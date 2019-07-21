package com.github.t45k.abcd.ast.constructor;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractASTConstructorTest {

    @Test
    public void testCreate() {
        final ASTConstructor nonBindingASTConstructor = ASTConstructor.create(null, null, null);
        assertThat(nonBindingASTConstructor).isInstanceOf(NonBindingASTConstructor.class);

        final Path tmpPath = Paths.get("");
        final ASTConstructor bindingASTConstructor = ASTConstructor.create(tmpPath, tmpPath, tmpPath);
        assertThat(bindingASTConstructor).isInstanceOf(BindingASTConstructor.class);
    }
}