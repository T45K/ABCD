package com.github.t45k.abcd.ast;

import java.nio.file.Path;

public class AbstractASTConstructor {
    public static AbstractASTConstructor create(final Path classpathRootPath, final Path libRootPath) {
        if (classpathRootPath == null || libRootPath == null) {
            return new ASTConstructor();
        }else {
            return new BindedASTConstructor(classpathRootPath,libRootPath);
        }
    }
}
