package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputTextTest {

    @Test
    public void testOutput1() throws IOException {
        final Path path = Paths.get("output", "test");
        final Output output = Output.create(Format.TXT);
        output.output(path, getCloneSets());

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
    public void testOutput2() throws IOException {
        final Path path = Paths.get("output", "output", "test");
        final Output output = Output.create(Format.TXT);
        output.output(path, getCloneSets());

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

    private Set<CloneSet> getCloneSets() {
        final CodeFragment codeFragment1 = new CodeFragment(Paths.get("a"), 0, 10, "a");
        final CodeFragment codeFragment2 = new CodeFragment(Paths.get("b"), 10, 20, "b");

        final Set<CodeFragment> codeFragments1 = new HashSet<>();
        codeFragments1.add(codeFragment1);
        codeFragments1.add(codeFragment2);

        final CodeFragment codeFragment3 = new CodeFragment(Paths.get("c"), 20, 30, "c");
        final CodeFragment codeFragment4 = new CodeFragment(Paths.get("d"), 30, 40, "d");

        final Set<CodeFragment> codeFragments2 = new HashSet<>();
        codeFragments2.add(codeFragment3);
        codeFragments2.add(codeFragment4);

        final CloneSet cloneSet1 = new CloneSet(codeFragments1);
        final CloneSet cloneSet2 = new CloneSet(codeFragments2);
        final Set<CloneSet> cloneSets = new HashSet<>();
        cloneSets.add(cloneSet1);
        cloneSets.add(cloneSet2);
        return cloneSets;
    }
}
