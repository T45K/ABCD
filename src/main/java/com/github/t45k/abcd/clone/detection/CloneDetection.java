package com.github.t45k.abcd.clone.detection;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.clone.detection.visitor.CodeFragmentFindingVisitor;
import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;

import java.util.Set;
import java.util.stream.Collectors;

public class CloneDetection implements ICloneDetection {
    private final DetectionMode mode;

    public CloneDetection(final DetectionMode mode) {
        this.mode = mode;
    }

    @Override
    public Set<CloneSet> detectClones(final Set<FileAST> fileASTs) {
        return fileASTs.stream()
                .flatMap(fileAST -> CodeFragmentFindingVisitor.findCodeFragments(this.mode, fileAST))
                .collect(Collectors.groupingBy(
                        CodeFragment::getHashValue,
                        Collectors.toSet()
                        )
                ).values()
                .stream()
                .map(CloneSet::new)
                .collect(Collectors.toSet());
    }
}
