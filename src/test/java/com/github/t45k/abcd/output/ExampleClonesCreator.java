package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;
import com.github.t45k.abcd.clone.entity.CodeFragment;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

class ExampleClonesCreator {

    static Set<CloneSet> getCloneSets() {
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
