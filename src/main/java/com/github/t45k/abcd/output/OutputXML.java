package com.github.t45k.abcd.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OutputXML extends Output {
    private final AtomicInteger index = new AtomicInteger(1);

    @Override
    String convertCloneSetsToString(final Set<com.github.t45k.abcd.clone.entity.CloneSet> cloneSets) {
        final List<CloneSet> cloneSetListForXML = cloneSets.stream()
                .map(this::convertCloneSetForXML)
                .collect(Collectors.toList());

        final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xmlMapper.writeValueAsString(cloneSetListForXML);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("failed to create XML");
        }
    }

    private CloneSet convertCloneSetForXML(final com.github.t45k.abcd.clone.entity.CloneSet cloneSet) {
        final Set<CodeFragment> codeFragments = cloneSet.getCloneSet().stream()
                .map(this::convertToThisCodeFragment)
                .collect(Collectors.toSet());
        return new CloneSet(index.getAndIncrement(), codeFragments);
    }

    private CodeFragment convertToThisCodeFragment(final com.github.t45k.abcd.clone.entity.CodeFragment codeFragment) {
        return new CodeFragment(codeFragment.getFilePath(), codeFragment.getStartLine(), codeFragment.getEndLine());
    }

    @Override
    String getExtension() {
        return ".xml";
    }

    private static class CloneSet {
        private final int index;
        private final Set<OutputXML.CodeFragment> clones;

        private CloneSet(final int index, final Set<OutputXML.CodeFragment> clones) {
            this.index = index;
            this.clones = clones;
        }

        public int getIndex() {
            return index;
        }

        public Set<OutputXML.CodeFragment> getClones() {
            return clones;
        }
    }

    private static class CodeFragment {
        private final String filePath;
        private final int startLine;
        private final int endLine;

        private CodeFragment(final Path filePath, final int startLine, final int endLine) {
            this.filePath = filePath.toString();
            this.startLine = startLine;
            this.endLine = endLine;
        }

        public String getFilePath() {
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
