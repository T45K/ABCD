package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public class OutputCSV extends Output {

    @Override
    protected String getExtension() {
        return ".csv";
    }

    @Override
    protected String convertCloneSetToString(final CloneSet cloneSet) {
        return null;
    }
}
