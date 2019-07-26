package com.github.t45k.abcd.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OutputJson extends Output {
    private final AtomicInteger index = new AtomicInteger(1);

    @Override
    String convertCloneSetsToString(final Set<CloneSet> cloneSets) {
        final List<CloneSetInJson> cloneSetsForJson = cloneSets.stream()
                .map(this::convertCloneSetForJson)
                .collect(Collectors.toList());

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectMapper.writeValueAsString(cloneSetsForJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("failed to create Json");
        }
    }

    @Override
    String getExtension() {
        return ".json";
    }

    private CloneSetInJson convertCloneSetForJson(final CloneSet cloneSet) {
        final Set<CodeFragmentInJson> clonesForJson = cloneSet.getCloneSet().stream()
                .map(this::convertCodeFragmentForJson)
                .collect(Collectors.toSet());

        return new CloneSetInJson(index.getAndIncrement(), clonesForJson);
    }

    private CodeFragmentInJson convertCodeFragmentForJson(final CodeFragment codeFragment) {
        return new CodeFragmentInJson(codeFragment.getFilePath(), codeFragment.getStartLine(), codeFragment.getEndLine());
    }

    private static class CloneSetInJson {
        private final int index;
        private final Set<CodeFragmentInJson> clones;

        private CloneSetInJson(final int index, final Set<CodeFragmentInJson> clones) {
            this.index = index;
            this.clones = clones;
        }

        public int getIndex() {
            return index;
        }

        public Set<CodeFragmentInJson> getClones() {
            return clones;
        }
    }

    private static class CodeFragmentInJson {
        private final Path filePath;
        private final int startLine;
        private final int endLine;

        private CodeFragmentInJson(final Path filePath, final int startLine, final int endLine) {
            this.filePath = filePath;
            this.startLine = startLine;
            this.endLine = endLine;
        }

        public Path getFilePath() {
            return filePath;
        }

        public int getStartLine() {
            return startLine;
        }

        public int getEndLine() {
            return endLine;
        }
    }
}
