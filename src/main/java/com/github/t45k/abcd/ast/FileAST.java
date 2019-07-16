package com.github.t45k.abcd.ast;

import org.eclipse.jdt.core.dom.CompilationUnit;

import java.nio.file.Path;

public class FileAST {
    private final Path path;
    private final CompilationUnit unit;

    public FileAST(final Path path, final CompilationUnit unit) {
        this.path = path;
        this.unit = unit;
    }

    public Path getPath() {
        return path;
    }

    public CompilationUnit getUnit() {
        return unit;
    }
}
