package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public interface Output {
    void output(final Path filePath, final Set<CloneSet> cloneSets) throws IOException;

    static Output create(final Format format) {
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
}
