package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ConfigTest;
import com.github.t45k.abcd.ast.FileAST;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.t45k.abcd.ast.constructor.ASTConstructor.INVALID_PATH_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BindingASTConstructorTest {
    private static final String BASIC_PATH = "sample/astConstructionSample/src";

    @Test
    public void testConstructAST() {
        final Path targetFilesRootPath = Paths.get(BASIC_PATH);
        final Config config = new ConfigTest().getBindingConfig(BASIC_PATH, ".", ".");
        final ASTConstructor astConstructor = new BindingASTConstructor(config);
        final Set<FileAST> fileASTList = astConstructor.constructFileAST(targetFilesRootPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).hasSize(3);
        assertThat(fileASTList).anyMatch(fileAST -> fileAST.getPath().equals(Paths.get(BASIC_PATH, "a", "A.java")));
        assertThat(fileASTList).anyMatch(fileAST -> fileAST.getPath().equals(Paths.get(BASIC_PATH, "a", "b", "B.java")));
        assertThat(fileASTList).anyMatch(fileAST -> fileAST.getPath().equals(Paths.get(BASIC_PATH, "c", "C.java")));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetTargetDirs() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Path targetFilesRootPath = Paths.get(BASIC_PATH);
        final Method getTargetDirs = BindingASTConstructor.class.getDeclaredMethod("getTargetDirs", Path.class);
        getTargetDirs.setAccessible(true);
        final Config config = new ConfigTest().getBindingConfig(BASIC_PATH, ".", ".");
        final ASTConstructor astConstructor = new BindingASTConstructor(config);
        final Set<Path> dirs = (Set<Path>) getTargetDirs.invoke(astConstructor, targetFilesRootPath);

        final Set<Path> paths = Stream.of(targetFilesRootPath, Paths.get(BASIC_PATH, "a"), Paths.get(BASIC_PATH, "a", "b"), Paths.get(BASIC_PATH, "c"), Paths.get(BASIC_PATH, "empty")).collect(Collectors.toSet());
        assertThat(dirs).isNotNull();
        assertThat(dirs).isNotEmpty();
        assertThat(dirs).hasSize(5);
        assertThat(dirs).isEqualTo(paths);
    }

    @Test
    public void testException() {
        final Path targetFilesRootPath = Paths.get(BASIC_PATH);
        final Path invalidPath = Paths.get("invalidPath");
        final Config config = new ConfigTest().getBindingConfig(invalidPath.toString(), ".", ".");
        final ASTConstructor astConstructor = new BindingASTConstructor(config);
        assertThatThrownBy(() -> astConstructor.constructFileAST(targetFilesRootPath))
                .isInstanceOfSatisfying(RuntimeException.class, e -> assertThat(e.getMessage()).isEqualTo(INVALID_PATH_EXCEPTION_MESSAGE));
    }
}