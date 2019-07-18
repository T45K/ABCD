package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.ast.FileAST;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.github.t45k.abcd.ast.constructor.ASTConstructor.INVALID_PATH_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NonBindingASTConstructorTest {
    private static String BASIC_PATH = "sample/astConstructionSample/src";

    @Test
    public void testConstructAST() {
        final IASTConstructor astConstructor = new NonBindingASTConstructor();
        final Path targetFilesRootPath = Paths.get(BASIC_PATH);
        final List<FileAST> fileASTList = astConstructor.constructFileAST(targetFilesRootPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).hasSize(3);
        assertThat(fileASTList).anyMatch(fileAST -> fileAST.getPath().equals(Paths.get(BASIC_PATH, "a", "A.java")));
        assertThat(fileASTList).anyMatch(fileAST -> fileAST.getPath().equals(Paths.get(BASIC_PATH, "a", "b", "B.java")));
        assertThat(fileASTList).anyMatch(fileAST -> fileAST.getPath().equals(Paths.get(BASIC_PATH, "c", "C.java")));
    }

    @Test
    public void testConstructEmptyList() {
        final IASTConstructor astConstructor = new NonBindingASTConstructor();
        final Path emptyDirPath = Paths.get(BASIC_PATH, "empty");
        final List<FileAST> fileASTList = astConstructor.constructFileAST(emptyDirPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).isEmpty();
    }

    @Test
    public void testException() {
        final IASTConstructor astConstructor = new NonBindingASTConstructor();
        final Path targetFilesRootPath = Paths.get("invalidPath");
        assertThatThrownBy(() -> astConstructor.constructFileAST(targetFilesRootPath))
                .isInstanceOfSatisfying(RuntimeException.class, e -> assertThat(e.getMessage()).isEqualTo(INVALID_PATH_EXCEPTION_MESSAGE));
    }
}
