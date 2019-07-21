package com.github.t45k.abcd.clone.detection.visitor;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.clone.detection.normalizer.CodeFragmentNormalizer;
import com.github.t45k.abcd.clone.entity.CodeFragment;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.WhileStatement;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CodeFragmentFindingVisitor extends ASTVisitor {
    private final CodeFragmentNormalizer normalizer;
    private final CompilationUnit unit;
    private final Path filePath;
    private final Set<CodeFragment> codeFragments;

    private CodeFragmentFindingVisitor(final DetectionMode mode, final FileAST fileAST) {
        this.normalizer = CodeFragmentNormalizer.create(mode);
        this.unit = fileAST.getUnit();
        this.filePath = fileAST.getPath();
        this.codeFragments = new HashSet<>();
    }

    public static Stream<CodeFragment> findCodeFragments(final DetectionMode mode, final FileAST fileAST) {
        final CodeFragmentFindingVisitor visitor = new CodeFragmentFindingVisitor(mode, fileAST);
        fileAST.getUnit().accept(visitor);

        return visitor.getCloneSetMap().stream();
    }

    private Set<CodeFragment> getCloneSetMap() {
        return this.codeFragments;
    }

    private void addCodeFragments(final ASTNode node) {
        final String normalizedFragment;
        try {
            normalizedFragment = this.normalizer.normalize(node);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return;
        }
        final String hashValue = DigestUtils.sha256Hex(normalizedFragment);
        final int startPosition = node.getStartPosition();
        final int startLineNumber = this.unit.getLineNumber(startPosition);
        final int endPosition = startPosition + node.getLength() - 1;
        final int endLineNumber = this.unit.getLineNumber(endPosition);

        final CodeFragment codeFragment = new CodeFragment(hashValue, this.filePath, startLineNumber, endLineNumber, node.toString());
        this.codeFragments.add(codeFragment);
    }

    @Override
    public boolean visit(final DoStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final ForStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final IfStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final SwitchStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final SynchronizedStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final TryStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final WhileStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final MethodDeclaration node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final TypeDeclaration node) {
        addCodeFragments(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(final EnhancedForStatement node) {
        addCodeFragments(node);
        return super.visit(node);
    }
}
