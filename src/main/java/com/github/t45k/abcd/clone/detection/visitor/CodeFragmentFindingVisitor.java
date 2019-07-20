package com.github.t45k.abcd.clone.detection.visitor;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.clone.entity.CodeFragment;
import org.eclipse.jdt.core.dom.ASTVisitor;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CodeFragmentFindingVisitor extends ASTVisitor {
    private final DetectionMode mode;
    private final Path filePath;
    private final Set<CodeFragment> codeFragments;

    public CodeFragmentFindingVisitor(final DetectionMode mode, final Path filePath) {
        this.mode = mode;
        this.filePath = filePath;
        this.codeFragments = new HashSet<>();
    }

    public static Stream<CodeFragment> findCodeFragments(final DetectionMode mode, final FileAST fileAST) {
        final Path filePath = fileAST.getPath();
        final CodeFragmentFindingVisitor visitor = new CodeFragmentFindingVisitor(mode, filePath);
        fileAST.getUnit().accept(visitor);

        return visitor.codeFragments.stream();
    }

    public Set<CodeFragment> getCloneSetMap() {
        return this.codeFragments;
    }
}
