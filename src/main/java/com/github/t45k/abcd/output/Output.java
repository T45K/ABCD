package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public abstract class Output {

    public static Output create(final Format format) {
        switch (format) {
            case JSON:
                return new OutputJson();
            case CSV:
                return new OutputCSV();
            case TXT:
                return new OutputText();
            case XML:
                return new OutputXML();
        }

        throw new RuntimeException("You could not come here");
    }

    public void output(final Path filePath, final Set<CloneSet> cloneSets) throws IOException {
        final String fileContents = convertCloneSetsToString(cloneSets);

        final Path outputFilePath = Paths.get(filePath.toString() + getExtension());
        createFile(outputFilePath);

        Files.write(outputFilePath, fileContents.getBytes());
    }

    private void createFile(final Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            return;
        }

        if (filePath.getParent() != null && !Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        Files.createFile(filePath);
    }

    abstract String convertCloneSetsToString(final Set<CloneSet> cloneSets);

    abstract String getExtension();
}
