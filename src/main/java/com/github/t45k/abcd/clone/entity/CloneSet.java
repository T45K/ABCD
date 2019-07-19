package com.github.t45k.abcd.clone.entity;

import java.util.HashSet;
import java.util.Set;

public class CloneSet {
    private final Set<CodeFragment> cloneSet = new HashSet<>();

    public void add(final CodeFragment codeFragment) {
        this.cloneSet.add(codeFragment);
    }
}
