package com.github.t45k.abcd.clone.detection.normalizer;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.TypeLiteral;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OriginalNormalizer extends ASTVisitor implements CodeFragmentNormalizer {
    private ASTNode codeFragment;
    private ASTNode copiedFragment;
    private Map<IBinding, Integer> specialCharacterMap = new HashMap<>();
    private int variableCounter;

    @Override
    public String normalize(final ASTNode codeFragment) {
        this.codeFragment = codeFragment;
        copiedFragment = ASTNode.copySubtree(codeFragment.getAST(), codeFragment);
        codeFragment.accept(this);
        return copiedFragment.toString();
    }

    private SimpleName createSpecialCharacter(final ASTNode node, final IBinding binding) {
        final int index = specialCharacterMap.computeIfAbsent(binding, i -> variableCounter++);

        return node.getAST().newSimpleName("$" + index);
    }

    private boolean isFailedBinding(final Name node) {
        return node.resolveBinding() == null || node.resolveTypeBinding() == null;
    }

    private boolean isFailedBinding(final FieldAccess node) {
        return node.resolveFieldBinding() == null || node.resolveTypeBinding() == null;
    }

    @Override
    public boolean visit(final SimpleName node) {
        visitName(node);
        return false;
    }

    @Override
    public boolean visit(final QualifiedName node) {
        visitName(node);
        return false;
    }

    private void visitName(final Name node) {
        if (isFailedBinding(node)) {
            return;
        }

        final IBinding binding = node.resolveBinding();
        if (binding.getKind() == IBinding.VARIABLE) {
            replaceNode(getNodeInCopiedStatement(node, codeFragment, copiedFragment), createSpecialCharacter(node, binding));
        }
    }

    @Override
    public boolean visit(final FieldAccess node) {
        if (isFailedBinding(node)) {
            return false;
        }

        final IVariableBinding binding = node.resolveFieldBinding();
        replaceNode(getNodeInCopiedStatement(node, codeFragment, copiedFragment), createSpecialCharacter(node, binding));

        return false;
    }

    @Override
    public boolean visit(final SwitchCase node) {
        return false;
    }

    @Override
    public boolean visit(final NumberLiteral node) {
        visitLiteral(node);
        return false;
    }

    @Override
    public boolean visit(final StringLiteral node) {
        visitLiteral(node);
        return false;
    }

    @Override
    public boolean visit(final CharacterLiteral node) {
        visitLiteral(node);
        return false;
    }

    @Override
    public boolean visit(final TypeLiteral node) {
        visitLiteral(node);
        return false;
    }

    @Override
    public boolean visit(final BooleanLiteral node) {
        visitLiteral(node);
        return false;
    }

    private void visitLiteral(final Expression node) {
        final SimpleName simpleName = node.getAST().newSimpleName("$");
        replaceNode(getNodeInCopiedStatement(node, codeFragment, copiedFragment), simpleName);
    }

    @SuppressWarnings("unchecked")
    private void replaceNode(final ASTNode oldNode, final ASTNode newNode) {
        final StructuralPropertyDescriptor locationInParent = oldNode.getLocationInParent();

        final ASTNode copiedNode = ASTNode.copySubtree(oldNode.getAST(), newNode);

        if (locationInParent.isChildListProperty()) {
            final List<ASTNode> siblings = (List<ASTNode>) oldNode.getParent().getStructuralProperty(locationInParent);
            final int replaceIdx = siblings.indexOf(oldNode);
            siblings.set(replaceIdx, copiedNode);
        } else if (locationInParent.isChildProperty()) {
            oldNode.getParent().setStructuralProperty(locationInParent, copiedNode);
        } else {
            throw new RuntimeException("can't replace node");
        }
    }

    private ASTNode getNodeInCopiedStatement(final ASTNode targetNode, final ASTNode statement, final ASTNode copiedStatement) {
        final Deque<StructuralPropertyDescriptor> structuralPropertyDescriptorStack = new ArrayDeque<>();
        final Deque<Integer> indexStack = new ArrayDeque<>();
        pushTargetNodeLocation(targetNode, statement, structuralPropertyDescriptorStack, indexStack);

        return popTargetNode(copiedStatement, structuralPropertyDescriptorStack, indexStack);
    }

    @SuppressWarnings("unchecked")
    private void pushTargetNodeLocation(final ASTNode targetNode, final ASTNode statement, final Deque<StructuralPropertyDescriptor> structuralStack, final Deque<Integer> indexStack) {
        ASTNode repetitionChangingNode = targetNode;
        while (repetitionChangingNode != statement) {
            final StructuralPropertyDescriptor locationInParent = repetitionChangingNode.getLocationInParent();
            structuralStack.push(locationInParent);
            if (locationInParent.isChildListProperty()) {
                List<ASTNode> list = (List<ASTNode>) repetitionChangingNode.getParent().getStructuralProperty(locationInParent);
                indexStack.push(list.indexOf(repetitionChangingNode));
            }
            repetitionChangingNode = repetitionChangingNode.getParent();
        }
    }

    @SuppressWarnings("unchecked")
    private ASTNode popTargetNode(final ASTNode copiedStatement, final Deque<StructuralPropertyDescriptor> structuralStack, final Deque<Integer> indexStack) {
        ASTNode target = copiedStatement;
        while (structuralStack.size() != 0) {
            final StructuralPropertyDescriptor locationInParent = structuralStack.pop();
            if (locationInParent.isChildListProperty()) {
                final Integer index = indexStack.poll();
                if (index != null) {
                    target = ((List<ASTNode>) target.getStructuralProperty(locationInParent)).get(index);
                }
            } else if (locationInParent.isChildProperty()) {
                target = (ASTNode) target.getStructuralProperty(locationInParent);
            } else {
                throw new RuntimeException("can't get node");
            }
        }

        return target;
    }
}
