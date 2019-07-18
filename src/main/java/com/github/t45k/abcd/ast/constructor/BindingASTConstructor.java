package com.github.t45k.abcd.ast.constructor;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        setParseEnvironment(parser);

        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);

        return parser;
    }

    private void setParseEnvironment(final ASTParser parser) {
        final List<Path> classpathList = getTargetFiles(this.libRootPath, ".jar");
        classpathList.add(this.classpathRootPath);
        final String[] classpathEntries = convertPathListToStringArray(classpathList);

        final List<Path> sourcePathList = getTargetDirs(this.targetFileRootPath);
        final String[] sourcePathEntries = convertPathListToStringArray(sourcePathList);

        parser.setEnvironment(classpathEntries, sourcePathEntries, null, false);
    }

    private List<Path> getTargetDirs(final Path rootPath) {
        try {
            return Files.walk(rootPath)
                    .filter(Files::isDirectory)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("invalid argument was specified");
        }
    }
}
