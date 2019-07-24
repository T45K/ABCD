package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public class OutputXML extends AbstractOutput {

    @Override
    protected String getExtension() {
        return ".xml";
    }

    @Override
    public String convertCloneSetToString(final CloneSet cloneSet) {
        return null;
    }
}
