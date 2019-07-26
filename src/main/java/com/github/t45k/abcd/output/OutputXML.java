package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public class OutputXML extends Output {

    @Override
    String convertCloneSetsToString(final Set<CloneSet> cloneSets) {
        return null;
    }

    @Override
    String getExtension() {
        return ".xml";
    }
}
