package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractOutput implements Output {

    @Override
    public void output(final Path filePath, final Set<CloneSet> cloneSets) throws IOException {
        final String fileContents = cloneSets.stream()
                .map(this::convertCloneSetToString)
                .collect(Collectors.joining("\n\n"));

        final Path outputFilePath = filePath.resolve(getExtension());

        Files.write(outputFilePath, fileContents.getBytes());
    }

    protected abstract String convertCloneSetToString(final CloneSet cloneSet);

    protected abstract String getExtension();
}
