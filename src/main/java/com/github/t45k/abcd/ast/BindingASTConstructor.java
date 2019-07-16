package com.github.t45k.abcd.ast;

import java.nio.file.Path;

public class BindingASTConstructor extends AbstractASTConstructor {
    private final Path classpathRootPath;
    private final Path libRootPath;

    public BindingASTConstructor(final Path classpathRootPath, final Path libRootPath) {
        this.classpathRootPath = classpathRootPath;
        this.libRootPath = libRootPath;
    }
}
