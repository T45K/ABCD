package com.github.t45k.abcd.clone.detection;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.clone.entity.CloneSet;

import java.util.Set;

public interface ICloneDetection {
    public Set<CloneSet> detectClones(final Set<FileAST> fileASTs);
}