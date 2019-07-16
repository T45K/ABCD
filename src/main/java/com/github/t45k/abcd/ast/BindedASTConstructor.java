package com.github.t45k.abcd.ast;

import java.nio.file.Path;

public class BindedASTConstructor extends AbstractASTConstructor {
    private final Path classpathRootPath;
    private final Path libRootPath;

    public BindedASTConstructor(final Path classpathRootPath, final Path libRootPath) {
        this.classpathRootPath = classpathRootPath;
        this.libRootPath = libRootPath;
    }
}
