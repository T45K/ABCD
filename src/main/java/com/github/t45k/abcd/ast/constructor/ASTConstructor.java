package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.ast.FileAST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.nio.file.Path;
import java.util.Set;

public interface ASTConstructor {
    Set<FileAST> constructFileAST(final Path targetFilesRootPath);

    ASTParser createParser();

    static ASTConstructor create(final Path targetFilesRootPath, final Path classpathRootPath, final Path libRootPath) {
        if (classpathRootPath == null || libRootPath == null) {
            return new NonBindingASTConstructor();
        }
        return new BindingASTConstructor(targetFilesRootPath, classpathRootPath, libRootPath);
    }
}
