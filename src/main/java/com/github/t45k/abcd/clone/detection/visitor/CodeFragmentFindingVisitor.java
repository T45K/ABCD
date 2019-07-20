package com.github.t45k.abcd.clone.detection.visitor;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.clone.detection.normalizer.CodeFragmentNormalizer;
import com.github.t45k.abcd.clone.entity.CodeFragment;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Statement;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CodeFragmentFindingVisitor extends ASTVisitor {
    private final CodeFragmentNormalizer normalizer;
    private final Path filePath;
    private final Set<CodeFragment> codeFragments;

    private CodeFragmentFindingVisitor(final DetectionMode mode, final Path filePath) {
        this.normalizer = CodeFragmentNormalizer.create(mode);
        this.filePath = filePath;
        this.codeFragments = new HashSet<>();
    }

    public static Stream<CodeFragment> findCodeFragments(final DetectionMode mode, final FileAST fileAST) {
        final Path filePath = fileAST.getPath();
        final CodeFragmentFindingVisitor visitor = new CodeFragmentFindingVisitor(mode, filePath);
        fileAST.getUnit().accept(visitor);

        return visitor.getCloneSetMap().stream();
    }

    private Set<CodeFragment> getCloneSetMap() {
        return this.codeFragments;
    }

    private void addCodeFragments(final Statement codeFragment) {

    }

    @Override
    public boolean visit(final DoStatement node) {
        return super.visit(node);
    }
}
