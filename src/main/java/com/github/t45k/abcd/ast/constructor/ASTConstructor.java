package com.github.t45k.abcd.ast.constructor;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.nio.file.Path;
import java.util.Map;

public class ASTConstructor extends AbstractASTConstructor {
    private final Path targetFileRootPath;

    public ASTConstructor(final Path targetFileRootPath) {
        this.targetFileRootPath = targetFileRootPath;
    }

    @Override
    public ASTParser createParser() {
        final ASTParser parser = ASTParser.newParser(AST.JLS10);
        final Map<String, String> options = getOptionMap();
        parser.setCompilerOptions(options);

        return parser;
    }
}
