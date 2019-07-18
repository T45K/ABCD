package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.ast.FileAST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.nio.file.Path;
import java.util.List;

public interface IASTConstructor {
    public List<FileAST> constructFileAST(final Path targetFilesRootPath);

    public ASTParser createParser();
}
