package com.github.t45k.abcd.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OutputJson extends Output {

    @Override
    public void output(final Path filePath, final Set<CloneSet> cloneSets) throws IOException {
    }

    @Override
    protected String getExtension() {
        return ".json";
    }

    @Override
    protected String convertCloneSetToString(final CloneSet cloneSet) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final List<String> json = new ArrayList<>();

        for (final CodeFragment codeFragment : cloneSet.getCloneSet()) {
            final JsonField jsonField = new JsonField(codeFragment.getFilePath(), codeFragment.getStartLine(), codeFragment.getEndLine());
            try {
                json.add(objectMapper.writeValueAsString(jsonField));
            } catch (final JsonProcessingException e) {
                final String elements = "{\n" +
                        "filePath : " + jsonField.getFilePath() + ",\n" +
                        "startLine : " + jsonField.getStartLine() + ",\n" +
                        "endLine : " + jsonField.getEndLine() + ",\n" +
                        "}";

                json.add(elements);
            }
        }
        return String.join("\n", json);
    }

    private static class JsonField {
        private final Path filePath;
        private final int startLine;
        private final int endLine;

        private JsonField(final Path filePath, final int startLine, final int endLine) {
            this.filePath = filePath;
            this.startLine = startLine;
            this.endLine = endLine;
        }

        private Path getFilePath() {
            return filePath;
        }

        private int getStartLine() {
            return startLine;
        }

        private int getEndLine() {
            return endLine;
        }
    }
}
