package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public class OutputText extends AbstractOutput {

    @Override
    protected String getExtension() {
        return ".txt";
    }

    @Override
    public String convertCloneSetToString(final CloneSet cloneSet) {
        return null;
    }
}
