package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public class OutputJson extends Output {

    @Override
    protected String getExtension() {
        return ".json";
    }

    @Override
    protected String convertCloneSetToString(final CloneSet cloneSet) {
        return null;
    }
}
