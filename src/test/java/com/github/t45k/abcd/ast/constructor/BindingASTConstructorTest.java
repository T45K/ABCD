package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.ast.FileAST;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.github.t45k.abcd.ast.constructor.ASTConstructor.INVALID_PATH_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BindingASTConstructorTest {
    private static final String BASIC_PATH = "sample/astConstructionSample/src";

    @Test
    public void testConstructAST() {
        final Path targetFilesRootPath = Paths.get(BASIC_PATH);
        final IASTConstructor astConstructor = new BindingASTConstructor(targetFilesRootPath, targetFilesRootPath, targetFilesRootPath);
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
        final ASTConstructor astConstructor = new BindingASTConstructor(targetFilesRootPath, targetFilesRootPath, targetFilesRootPath);
        final List<Path> dirs = (List<Path>) getTargetDirs.invoke(astConstructor, targetFilesRootPath);

        assertThat(dirs).isNotNull();
        assertThat(dirs).isNotEmpty();
        assertThat(dirs).hasSize(5);
        assertThat(dirs).anyMatch(path -> path.equals(Paths.get(BASIC_PATH, "a")));
        assertThat(dirs).anyMatch(path -> path.equals(Paths.get(BASIC_PATH, "a", "b")));
        assertThat(dirs).anyMatch(path -> path.equals(Paths.get(BASIC_PATH, "c")));
        assertThat(dirs).anyMatch(path -> path.equals(Paths.get(BASIC_PATH)));
        assertThat(dirs).anyMatch(path -> path.equals(Paths.get(BASIC_PATH, "empty")));
    }

    @Test
    public void testException() {
        final Path targetFilesRootPath = Paths.get(BASIC_PATH);
        final Path invalidPath = Paths.get("invalidPath");
        final IASTConstructor astConstructor = new BindingASTConstructor(invalidPath, targetFilesRootPath, targetFilesRootPath);
        assertThatThrownBy(() -> astConstructor.constructFileAST(targetFilesRootPath))
                .isInstanceOfSatisfying(RuntimeException.class, e -> assertThat(e.getMessage()).isEqualTo(INVALID_PATH_EXCEPTION_MESSAGE));
    }
}