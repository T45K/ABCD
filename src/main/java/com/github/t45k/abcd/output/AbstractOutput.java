package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class AbstractOutput implements Output {

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

    protected abstract String convertCloneSetToString(final CloneSet cloneSet);

    protected abstract String getExtension();

    private void createFile(final Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            return;
        }

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectory(filePath.getParent());
        }

        Files.createFile(filePath);
    }
}
