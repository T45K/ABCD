package com.github.t45k.abcd.ast.constructor;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ConfigTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ASTConstructorTest {

    @Test
    public void testCreate() {
        final ConfigTest configTest = new ConfigTest();
        final Config nullPathConfig = configTest.getNoBinaryAndLibConfig();
        final ASTConstructor nonBindingASTConstructor = ASTConstructor.create(nullPathConfig);
        assertThat(nonBindingASTConstructor).isInstanceOf(NonBindingASTConstructor.class);

        final Config pathConfig = configTest.getBinaryAndLibConfig("", "");
        final ASTConstructor bindingASTConstructor = ASTConstructor.create(pathConfig);
        assertThat(bindingASTConstructor).isInstanceOf(BindingASTConstructor.class);
    }
}