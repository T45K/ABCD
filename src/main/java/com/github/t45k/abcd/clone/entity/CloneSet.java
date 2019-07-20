package com.github.t45k.abcd.clone.entity;

import java.util.Set;

public class CloneSet {
    private final Set<CodeFragment> cloneSet;

    public CloneSet(final Set<CodeFragment> cloneSet) {
        this.cloneSet = cloneSet;
    }

    public Set<CodeFragment> getCloneSet() {
        return this.cloneSet;
    }
}
