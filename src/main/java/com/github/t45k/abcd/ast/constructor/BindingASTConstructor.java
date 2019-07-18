package com.github.t45k.abcd.ast.constructor;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class BindingASTConstructor extends AbstractASTConstructor {
    private final Path targetFileRootPath;
    private final Path classpathRootPath;
    private final Path libRootPath;

    BindingASTConstructor(final Path targetFileRootPath, final Path classpathRootPath, final Path libRootPath) {
        this.targetFileRootPath = targetFileRootPath;
        this.classpathRootPath = classpathRootPath;
        this.libRootPath = libRootPath;
    }

    @Override
    public ASTParser createParser() {
        final ASTParser parser = ASTParser.newParser(AST.JLS10);
        final Map<String, String> options = getOptionMap();
        parser.setCompilerOptions(options);

        final List<Path> classpathList = getTargetFiles(this.libRootPath, ".jar");
        classpathList.add(this.classpathRootPath);
        final String[] classpathEntries = convertPathListToStringArray(classpathList);
        parser.setEnvironment(classpathEntries, null, null, false);

        return parser;
    }
}
