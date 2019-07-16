package com.github.t45k.abcd.clone;

import java.nio.file.Path;

public class CodeFragment {
    private final String hashValue;
    private final Path filePath;
    private final int startLine;
    private final int endLine;
    private final String
            rawCode;

    public CodeFragment(final String hashValue, final Path filePath, final int startLine, final int endLine, final String rawCode) {
        this.hashValue = hashValue;
        this.filePath = filePath;
        this.startLine = startLine;
        this.endLine = endLine;
        this.rawCode = rawCode;
    }


    public String getHashValue() {
        return hashValue;
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

    public String getRawCode() {
        return rawCode;
    }
}
