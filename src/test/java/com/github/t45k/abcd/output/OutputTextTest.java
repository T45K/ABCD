package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OutputTextTest {
    @Test
    public void testOutput() throws IOException {
        final Path path = Paths.get("output", "test");
        final Output output = Output.create(Format.TXT);
        output.output(path,getCloneSets());

    }

    private Set<CloneSet> getCloneSets(){
        final CodeFragment codeFragment1 = new CodeFragment(Paths.get("a"), 0, 10, "a");
        final CodeFragment codeFragment2 = new CodeFragment(Paths.get("b"), 10, 20, "b");

        final Set<CodeFragment> codeFragments = new HashSet<>();
        codeFragments.add(codeFragment1);
        codeFragments.add(codeFragment2);

        final CloneSet cloneSet = new CloneSet(codeFragments);
        final Set<CloneSet> cloneSets = new HashSet<>();
        cloneSets.add(cloneSet);
        return cloneSets;
    }
}