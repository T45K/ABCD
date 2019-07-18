package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.ast.FileAST;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ASTConstructorTest {
    private static String basicPath = "sample/astConstructionSample/src";

    @Test
    public void testConstructAST() {
        final ASTConstructor astConstructor = new ASTConstructor();
        final Path targetPath = Paths.get(basicPath);
        final List<FileAST> fileASTList = astConstructor.constructFileAST(targetPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).hasSize(3);
        assertThat(fileASTList.get(0).getPath()).isEqualTo(Paths.get(basicPath, "a", "A.java"));
        assertThat(fileASTList.get(1).getPath()).isEqualTo(Paths.get(basicPath, "a", "b", "B.java"));
        assertThat(fileASTList.get(2).getPath()).isEqualTo(Paths.get(basicPath, "c", "C.java"));
    }

    @Test
    public void testConstructEmptyList() {
        final ASTConstructor astConstructor = new ASTConstructor();
        final Path targetPath = Paths.get(basicPath, "empty");
        final List<FileAST> fileASTList = astConstructor.constructFileAST(targetPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).isEmpty();
    }

    @Test
    public void testException() {
        final ASTConstructor astConstructor = new ASTConstructor();
        final Path targetPath = Paths.get("invalidPath");
        assertThatThrownBy(() -> astConstructor.constructFileAST(targetPath))
                .isInstanceOfSatisfying(RuntimeException.class, e -> assertThat(e.getMessage()).isEqualTo("Invalid path was specified"));
    }
}
