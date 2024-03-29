package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ast.FileAST;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ASTConstructor {
    static final String INVALID_PATH_EXCEPTION_MESSAGE = "Invalid path was specified";

    public static ASTConstructor create(final Config config) {
        if (config.getBinaryDir() == null || config.getLibDir() == null) {
            return new NonBindingASTConstructor();
        }
        return new BindingASTConstructor(config);
    }

    public Set<FileAST> constructFileAST(final Path targetFilesRootPath) {
        final ASTParser parser = createParser();

        final Set<FileAST> fileASTs = new HashSet<>();
        final FileASTRequestor requestor = new FileASTRequestor() {
            @Override
            public void acceptAST(final String sourceFilePath, final CompilationUnit ast) {
                ast.recordModifications();
                fileASTs.add(new FileAST(Paths.get(sourceFilePath), ast));
            }
        };

        final Set<Path> targetFilePaths = getTargetFiles(targetFilesRootPath, ".java");
        final String[] sourceFilePaths = convertPathsToStringArray(targetFilePaths);
        parser.createASTs(sourceFilePaths, null, new String[]{}, requestor, new NullProgressMonitor());

        return fileASTs;
    }

    @SuppressWarnings("unchecked")
    Map<String, String> getOptionMap() {
        final Map<String, String> options = DefaultCodeFormatterConstants.getEclipseDefaultSettings();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_10);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_10);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_10);
        return options;
    }

    Set<Path> getTargetFiles(final Path targetFileRootPath, final String targetExtension) {
        try {
            return Files.walk(targetFileRootPath)
                    .filter(path -> path.toString().endsWith(targetExtension))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(INVALID_PATH_EXCEPTION_MESSAGE);
        }
    }

    String[] convertPathsToStringArray(final Set<Path> paths) {
        return paths.stream()
                .map(Path::toString)
                .toArray(String[]::new);
    }

    abstract ASTParser createParser();
}
