package com.github.t45k.abcd.output;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputTextTest {

    @Test
    public void testOutput() throws IOException {
        final Path path = Paths.get("output", "test");
        final Output output = Output.create(Format.TXT);
        output.output(path, ExampleClonesCreator.getCloneSets());

        final Path outputPath = Paths.get(path.toString() + ".txt");

        assertThat(outputPath).exists();

        final String outputContents = new String(Files.readAllBytes(outputPath));
        Files.delete(outputPath);
        Files.delete(outputPath.getParent());

        assertThat(outputContents).contains("clone set 1");
        assertThat(outputContents).contains("a 0 10");
        assertThat(outputContents).contains("b 10 20");
        assertThat(outputContents).contains("c 20 30");
        assertThat(outputContents).contains("d 30 40");
    }

    @Test
    public void testOutputInDeepHierarchy() throws IOException {
        final Path path = Paths.get("output", "output", "test");
        final Output output = Output.create(Format.TXT);
        output.output(path, ExampleClonesCreator.getCloneSets());

        final Path outputPath = Paths.get(path.toString() + ".txt");

        assertThat(outputPath).exists();

        final String outputContents = new String(Files.readAllBytes(outputPath));
        Files.delete(outputPath);
        Files.delete(outputPath.getParent());
        Files.delete(outputPath.getParent().getParent());

        assertThat(outputContents).contains("clone set 1");
        assertThat(outputContents).contains("a 0 10");
        assertThat(outputContents).contains("b 10 20");
        assertThat(outputContents).contains("c 20 30");
        assertThat(outputContents).contains("d 30 40");
    }

    @Test
    public void testOutputInExistingFile() throws IOException {
        final Path path = Paths.get("output", "test");
        final Output output = Output.create(Format.TXT);
        final Path outputPath = Paths.get(path.toString() + ".txt");
        Files.createDirectory(outputPath.getParent());
        Files.createFile(outputPath);

        output.output(path, ExampleClonesCreator.getCloneSets());

        assertThat(outputPath).exists();

        final String outputContents = new String(Files.readAllBytes(outputPath));
        Files.delete(outputPath);
        Files.delete(outputPath.getParent());

        assertThat(outputContents).contains("clone set 1");
        assertThat(outputContents).contains("a 0 10");
        assertThat(outputContents).contains("b 10 20");
        assertThat(outputContents).contains("c 20 30");
        assertThat(outputContents).contains("d 30 40");
    }

    @Test
    public void testOutputHasNoParent() throws IOException {
        final Path path = Paths.get("output");
        final Output output = Output.create(Format.TXT);
        final Path outputPath = Paths.get(path.toString() + ".txt");

        output.output(path, ExampleClonesCreator.getCloneSets());

        assertThat(outputPath).exists();

        final String outputContents = new String(Files.readAllBytes(outputPath));
        Files.delete(outputPath);

        assertThat(outputContents).contains("clone set 1");
        assertThat(outputContents).contains("a 0 10");
        assertThat(outputContents).contains("b 10 20");
        assertThat(outputContents).contains("c 20 30");
        assertThat(outputContents).contains("d 30 40");
    }
}
