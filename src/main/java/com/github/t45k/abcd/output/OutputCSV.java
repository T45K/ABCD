package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public class OutputCSV extends AbstractOutput {
    @Override
    public void output(final CloneSet cloneSet) {

    }

    @Override
    protected String getExtension() {
        return ".csv";
    }
}
