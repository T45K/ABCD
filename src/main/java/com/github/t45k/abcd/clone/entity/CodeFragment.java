package com.github.t45k.abcd.clone.entity;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.file.Path;

public class CodeFragment {
    private final Path filePath;
    private final int startLine;
    private final int endLine;
    private final String rawCode;
    private final String hashValue;

    public CodeFragment(final Path filePath, final int startLine, final int endLine, final String rawCode) {
        this.filePath = filePath;
        this.startLine = startLine;
        this.endLine = endLine;
        this.rawCode = rawCode;
        this.hashValue = DigestUtils.md5Hex(rawCode);
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

    public String getHashValue() {
        return hashValue;
    }

    @Override
    public String toString() {
        return this.rawCode;
    }
}
