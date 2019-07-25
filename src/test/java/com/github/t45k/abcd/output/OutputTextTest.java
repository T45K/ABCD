package com.github.t45k.abcd.output;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OutputTextTest {
    @Test
    public void testOutput() {
        Paths.get("testOutput", "test");
    }
}