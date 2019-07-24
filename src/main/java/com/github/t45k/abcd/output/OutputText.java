package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.util.stream.Collectors;

public class OutputText extends AbstractOutput {

    @Override
    protected String getExtension() {
        return ".txt";
    }

    @Override
    protected String convertCloneSetToString(final CloneSet cloneSet) {
        return cloneSet.getCloneSet().stream()
                .map(f -> f.getFilePath() + " " + f.getStartLine() + " " + f.getEndLine())
                .collect(Collectors.joining("\n"));
    }
}
