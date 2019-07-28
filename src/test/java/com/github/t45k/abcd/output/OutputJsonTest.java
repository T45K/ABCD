package com.github.t45k.abcd.output;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputJsonTest {

    @Test
    public void testOutput() throws IOException {
        final Path path = Paths.get("output", "test");
        final Output output = Output.create(Format.JSON);
        output.output(path, ExampleClonesCreator.getCloneSets());

        final Path outputPath = Paths.get(path.toString() + ".json");

        assertThat(outputPath).exists();

        final String outputContents = new String(Files.readAllBytes(outputPath));
        Files.delete(outputPath);
        Files.delete(outputPath.getParent());

        final String clone1 = "{\n" +
                "    \"filePath\" : \"c\",\n" +
                "    \"startLine\" : 20,\n" +
                "    \"endLine\" : 30\n";

        final String clone2 = "{\n" +
                "    \"filePath\" : \"d\",\n" +
                "    \"startLine\" : 30,\n" +
                "    \"endLine\" : 40\n";

        final String clone3 = "{\n" +
                "    \"filePath\" : \"a\",\n" +
                "    \"startLine\" : 0,\n" +
                "    \"endLine\" : 10\n";

        final String clone4 = "{\n" +
                "    \"filePath\" : \"b\",\n" +
                "    \"startLine\" : 10,\n" +
                "    \"endLine\" : 20\n";

        assertThat(outputContents).contains(clone1);
        assertThat(outputContents).contains(clone2);
        assertThat(outputContents).contains(clone3);
        assertThat(outputContents).contains(clone4);
    }
}
