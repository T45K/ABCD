package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public class OutputXML extends Output {

    @Override
    public void output(final Path filePath, final Set<CloneSet> cloneSets) throws IOException {
    }

    @Override
    protected String getExtension() {
        return ".xml";
    }

    @Override
    protected String convertCloneSetToString(final CloneSet cloneSet) {
        return null;
    }
}
