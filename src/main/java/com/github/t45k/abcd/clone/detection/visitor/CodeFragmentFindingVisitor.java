package com.github.t45k.abcd.clone.detection.visitor;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.clone.detection.normalizer.CodeFragmentNormalizer;
import com.github.t45k.abcd.clone.entity.CodeFragment;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
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
    private final Config config;

    private CodeFragmentFindingVisitor(final DetectionMode mode, final FileAST fileAST, final Config config) {
        this.normalizer = CodeFragmentNormalizer.create(mode);
        this.unit = fileAST.getUnit();
        this.filePath = fileAST.getPath();
        this.codeFragments = new HashSet<>();
        this.config = config;
    }

    public static Stream<CodeFragment> findCodeFragments(final DetectionMode mode, final FileAST fileAST, final Config config) {
        final CodeFragmentFindingVisitor visitor = new CodeFragmentFindingVisitor(mode, fileAST, config);
        fileAST.getUnit().accept(visitor);

        return visitor.getCloneSetMap().stream();
    }

    private Set<CodeFragment> getCloneSetMap() {
        return this.codeFragments;
    }

    private void addCodeFragments(final ASTNode node) {
        if (!isBeyondThresholdToken(node.toString())) {
            return;
        }

        final String normalizedFragment;
        try {
            normalizedFragment = this.normalizer.normalize(node);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return;
        }

        final int startPosition = node.getStartPosition();
        final int startLineNumber = this.unit.getLineNumber(startPosition);
        final int endPosition = startPosition + node.getLength() - 1;
        final int endLineNumber = this.unit.getLineNumber(endPosition);

        if (endLineNumber - startLineNumber + 1 < config.getThresholdLine()) {
            return;
        }

        final CodeFragment codeFragment = new CodeFragment(this.filePath, startLineNumber, endLineNumber, normalizedFragment);
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

    private boolean isBeyondThresholdToken(final String codeFragment) {
        final IScanner scanner = ToolFactory.createScanner(false, false, true, false);
        scanner.setSource(codeFragment.toCharArray());

        int numOfToken = 0;
        try {
            while (scanner.getNextToken() != ITerminalSymbols.TokenNameEOF) {
                numOfToken++;
            }
        } catch (final InvalidInputException e) {
            return false;
        }

        return numOfToken >= config.getThresholdLine();
    }
}
