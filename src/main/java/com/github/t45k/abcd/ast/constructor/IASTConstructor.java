package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.ast.FileAST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.nio.file.Path;
import java.util.Set;

public interface IASTConstructor {
    Set<FileAST> constructFileAST(final Path targetFilesRootPath);

    ASTParser createParser();
}
