package com.github.t45k.abcd.output;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputXMLTest {
    @Test
    public void testOutput() throws IOException {
        final Path path = Paths.get("output", "test");
        final Output output = Output.create(Format.XML);
        output.output(path, ExampleClonesCreator.getCloneSets());

        final Path outputPath = Paths.get(path.toString() + ".xml");

        assertThat(outputPath).exists();

        final String outputContents = new String(Files.readAllBytes(outputPath));
        Files.delete(outputPath);
        Files.delete(outputPath.getParent());

        final String cloneA = "      <clones>\n" +
                "        <filePath>a</filePath>\n" +
                "        <startLine>0</startLine>\n" +
                "        <endLine>10</endLine>\n" +
                "      </clones>";

        final String cloneB = "      <clones>\n" +
                "        <filePath>b</filePath>\n" +
                "        <startLine>10</startLine>\n" +
                "        <endLine>20</endLine>\n" +
                "      </clones>";

        final String cloneC = "      <clones>\n" +
                "        <filePath>c</filePath>\n" +
                "        <startLine>20</startLine>\n" +
                "        <endLine>30</endLine>\n" +
                "      </clones>";

        final String cloneD =
                "      <clones>\n" +
                        "        <filePath>d</filePath>\n" +
                        "        <startLine>30</startLine>\n" +
                        "        <endLine>40</endLine>\n" +
                        "      </clones>";

        assertThat(outputContents).contains(cloneA);
        assertThat(outputContents).contains(cloneB);
        assertThat(outputContents).contains(cloneC);
        assertThat(outputContents).contains(cloneD);
    }
}
