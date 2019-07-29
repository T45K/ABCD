package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;

import java.util.Set;
import java.util.stream.Collectors;

public class OutputCSV extends Output {

    @Override
    String convertCloneSetsToString(final Set<CloneSet> cloneSets) {
        final String firstLine = "file path,start line,end line\n\n";
        final String table = cloneSets.stream()
                .map(this::convertCloneSetToString)
                .collect(Collectors.joining("\n\n"));

        return firstLine + table;
    }

    private String convertCloneSetToString(final CloneSet cloneSet) {
        return cloneSet.getCloneSet().stream()
                .map(this::convertElementsToString)
                .collect(Collectors.joining("\n"));
    }

    private String convertElementsToString(final CodeFragment codeFragment) {
        return codeFragment.getFilePath() + "," + codeFragment.getStartLine() + "," + codeFragment.getEndLine();
    }

    @Override
    String getExtension() {
        return ".csv";
    }
}
