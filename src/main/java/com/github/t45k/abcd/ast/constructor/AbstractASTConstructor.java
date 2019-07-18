package com.github.t45k.abcd.ast.constructor;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractASTConstructor {
    public static AbstractASTConstructor create(final Path targetFilesRootPath, final Path classpathRootPath, final Path libRootPath) {
        if (classpathRootPath == null || libRootPath == null) {
            return new ASTConstructor(targetFilesRootPath);
        } else {
            return new BindingASTConstructor(targetFilesRootPath, classpathRootPath, libRootPath);
        }
    }

    public abstract ASTParser createParser();

    @SuppressWarnings("unchecked")
    Map<String, String> getOptionMap() {
        final Map<String, String> options = DefaultCodeFormatterConstants.getEclipseDefaultSettings();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_10);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_10);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_10);
        return options;
    }

    public List<FileAST> constructFileAST(final Path targetFilesRootPath) {
        final ASTParser parser = createParser();

        final List<FileAST> fileASTList = new ArrayList<>();
        final FileASTRequestor requestor = new FileASTRequestor() {
            @Override
            public void acceptAST(final String sourceFilePath, final CompilationUnit ast) {
                fileASTList.add(new FileAST(Paths.get(sourceFilePath), ast));
            }
        };

        final List<Path> targetFilePathList = getTargetFiles(targetFilesRootPath, ".java");
        final String[] filePathStrings = convertPathListToStringArray(targetFilePathList);
        parser.createASTs(filePathStrings, null, new String[]{}, requestor, new NullProgressMonitor());

        return fileASTList;
    }

    List<Path> getTargetFiles(final Path targetFileRootPath, final String targetExtension) {
        try {
            return Files.walk(targetFileRootPath)
                    .filter(path -> path.toString().endsWith(targetExtension))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Invalid path was specified");
        }
    }

    String[] convertPathListToStringArray(final List<Path> pathList) {
        return pathList.stream()
                .map(Path::toString)
                .toArray(String[]::new);
    }
}
