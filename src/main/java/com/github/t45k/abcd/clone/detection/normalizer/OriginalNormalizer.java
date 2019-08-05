package com.github.t45k.abcd.clone.detection.normalizer;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class OriginalNormalizer extends ASTVisitor implements CodeFragmentNormalizer {
    @Override
    public String normalize(final ASTNode codeFragment) {
        final ASTNode copiedNode = ASTNode.copySubtree(codeFragment.getAST(), codeFragment);
        return null;
    }

    @SuppressWarnings("unchecked")
    public static ASTNode getNodeInCopiedStatement(final ASTNode targetNode, final ASTNode statement, final ASTNode copiedStatement) {
        final Deque<StructuralPropertyDescriptor> structuralPropertyDescriptorStack = new ArrayDeque<>();
        final Deque<Integer> indexStack = new ArrayDeque<>();
        ASTNode repetitionChangingNode = targetNode;
        while (repetitionChangingNode != statement) {
            final StructuralPropertyDescriptor locationInParent = repetitionChangingNode.getLocationInParent();
            structuralPropertyDescriptorStack.push(locationInParent);
            if (locationInParent.isChildListProperty()) {
                List<ASTNode> list = (List<ASTNode>) repetitionChangingNode.getParent().getStructuralProperty(locationInParent);
                indexStack.push(list.indexOf(repetitionChangingNode));
            }
            repetitionChangingNode = repetitionChangingNode.getParent();
        }

        return getTargetNode(copiedStatement, structuralPropertyDescriptorStack, indexStack);
    }

    @SuppressWarnings("unchecked")
    private static ASTNode getTargetNode(final ASTNode copiedStatement, final Deque<StructuralPropertyDescriptor> structuralStack, final Deque<Integer> indexStack){
        ASTNode target = copiedStatement;
        while (structuralStack.size() != 0) {
            final StructuralPropertyDescriptor locationInParent = structuralStack.pop();
            if (locationInParent.isChildListProperty()) {
                target = ((List<ASTNode>) target.getStructuralProperty(locationInParent)).get(indexStack.poll());
            } else if (locationInParent.isChildProperty()) {
                target = (ASTNode) target.getStructuralProperty(locationInParent);
            } else {
                throw new RuntimeException("can't get node");
            }
        }

        return target;
    }
}
