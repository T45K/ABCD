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
        final NonBindingASTConstructor nonBindingAstConstructor = new NonBindingASTConstructor();
        final Path targetPath = Paths.get(BASIC_PATH);
        final List<FileAST> fileASTList = nonBindingAstConstructor.constructFileAST(targetPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).hasSize(3);
        assertThat(fileASTList.get(0).getPath()).isEqualTo(Paths.get(BASIC_PATH, "a", "A.java"));
        assertThat(fileASTList.get(1).getPath()).isEqualTo(Paths.get(BASIC_PATH, "a", "b", "B.java"));
        assertThat(fileASTList.get(2).getPath()).isEqualTo(Paths.get(BASIC_PATH, "c", "C.java"));
    }

    @Test
    public void testConstructEmptyList() {
        final NonBindingASTConstructor nonBindingAstConstructor = new NonBindingASTConstructor();
        final Path targetPath = Paths.get(BASIC_PATH, "empty");
        final List<FileAST> fileASTList = nonBindingAstConstructor.constructFileAST(targetPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).isEmpty();
    }

    @Test
    public void testException() {
        final NonBindingASTConstructor nonBindingAstConstructor = new NonBindingASTConstructor();
        final Path targetPath = Paths.get("invalidPath");
        assertThatThrownBy(() -> nonBindingAstConstructor.constructFileAST(targetPath))
                .isInstanceOfSatisfying(RuntimeException.class, e -> assertThat(e.getMessage()).isEqualTo(INVALID_PATH_EXCEPTION_MESSAGE));
    }
}
