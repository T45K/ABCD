package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OutputText extends Output {
    private final AtomicInteger index = new AtomicInteger(1);

    @Override
    String convertCloneSetsToString(final Set<CloneSet> cloneSets) {
        return cloneSets.stream()
                .map(this::getTextStyle)
                .collect(Collectors.joining("\n\n"));
    }

    @Override
    String getExtension() {
        return ".txt";
    }

    private String getTextStyle(final CloneSet cloneSet) {
        return "clone set " + index.getAndIncrement() + "\n" + convertEachCloneSetToString(cloneSet);
    }

    private String convertEachCloneSetToString(final CloneSet cloneSet) {
        return cloneSet.getCloneSet().stream()
                .map(this::getCodeFragmentInformation)
                .collect(Collectors.joining("\n"));
    }

    private String getCodeFragmentInformation(final CodeFragment codeFragment) {
        return codeFragment.getFilePath() + " " + codeFragment.getStartLine() + " " + codeFragment.getEndLine();
    }
}
