package com.github.t45k.abcd.ast.constructor;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BindingASTConstructor extends ASTConstructor {
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

        setParseEnvironment(parser);

        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);

        return parser;
    }

    private void setParseEnvironment(final ASTParser parser) {
        final Set<Path> classpaths = getTargetFiles(this.libRootPath, ".jar");
        classpaths.add(this.classpathRootPath);
        final String[] classpathEntries = convertPathListToStringArray(classpaths);

        final Set<Path> sourcePaths = getTargetDirs(this.targetFileRootPath);
        final String[] sourcePathEntries = convertPathListToStringArray(sourcePaths);

        parser.setEnvironment(classpathEntries, sourcePathEntries, null, false);
    }

    private Set<Path> getTargetDirs(final Path rootPath) {
        try {
            return Files.walk(rootPath)
                    .filter(Files::isDirectory)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(INVALID_PATH_EXCEPTION_MESSAGE);
        }
    }
}
