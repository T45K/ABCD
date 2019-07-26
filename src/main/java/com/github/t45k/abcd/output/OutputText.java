package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OutputText extends Output {

    @Override
    public void output(final Path filePath, final Set<CloneSet> cloneSets) throws IOException {
        final AtomicInteger index = new AtomicInteger(1);
        final String fileContents = cloneSets.stream()
                .map(cloneSet -> "clone set " + index.getAndIncrement() + "\n" + convertCloneSetToString(cloneSet))
                .collect(Collectors.joining("\n\n"));

        final Path outputFilePath = Paths.get(filePath.toString() + getExtension());
        createFile(outputFilePath);

        Files.write(outputFilePath, fileContents.getBytes());
    }

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
