package com.github.t45k.abcd.clone.detection;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.clone.detection.visitor.CodeFragmentFindingVisitor;
import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;

import java.util.Set;
import java.util.stream.Collectors;

public class CloneDetection {
    private final DetectionMode mode;
    private final Config config;

    public CloneDetection(final DetectionMode mode, final Config config) {
        this.mode = mode;
        this.config = config;
    }

    public Set<CloneSet> detectClones(final Set<FileAST> fileASTs) {
        return fileASTs.stream()
                .flatMap(fileAST -> CodeFragmentFindingVisitor.findCodeFragments(this.mode, fileAST, config))
                .collect(Collectors.groupingBy(
                        CodeFragment::getHashValue,
                        Collectors.toSet()
                        )
                ).values()
                .stream()
                .filter(set -> set.size() > 1)
                .map(CloneSet::new)
                .collect(Collectors.toSet());
    }
}
