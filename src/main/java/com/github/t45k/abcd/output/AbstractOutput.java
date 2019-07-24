package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.nio.file.Path;
import java.util.Set;

public abstract class AbstractOutput implements Output {

    @Override
    public void output(final Path filePath, final Set<CloneSet> cloneSets) {

    }
}
