package com.github.t45k.abcd.output;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputCSVTest {

    @Test
    public void testOutput() throws IOException {
        final Path path = Paths.get("output", "test");
        final Output output = Output.create(Format.CSV);
        output.output(path, ExampleClonesCreator.getCloneSets());

        final Path outputPath = Paths.get(path.toString() + ".csv");

        assertThat(outputPath).exists();

        final String outputContents = new String(Files.readAllBytes(outputPath));
        Files.delete(outputPath);
        Files.delete(outputPath.getParent());

        assertThat(outputContents).contains("a,0,10");
        assertThat(outputContents).contains("b,10,20");
        assertThat(outputContents).contains("c,20,30");
        assertThat(outputContents).contains("d,30,40");
    }
}
