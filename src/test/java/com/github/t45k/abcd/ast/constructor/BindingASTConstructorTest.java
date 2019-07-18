package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.ast.FileAST;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BindingASTConstructorTest {
    private static final String basicPath = "sample/astConstructionSample/src";

    @Test
    public void testConstructAST() {
        final Path targetFilesRootPath = Paths.get(basicPath);
        final AbstractASTConstructor astConstructor = new BindingASTConstructor(targetFilesRootPath, targetFilesRootPath, targetFilesRootPath);
        final List<FileAST> fileASTList = astConstructor.constructFileAST(targetFilesRootPath);

        assertThat(fileASTList).isNotNull();
        assertThat(fileASTList).hasSize(3);
        assertThat(fileASTList.get(0).getPath()).isEqualTo(Paths.get(basicPath, "a", "A.java"));
        assertThat(fileASTList.get(1).getPath()).isEqualTo(Paths.get(basicPath, "a", "b", "B.java"));
        assertThat(fileASTList.get(2).getPath()).isEqualTo(Paths.get(basicPath, "c", "C.java"));
    }

}