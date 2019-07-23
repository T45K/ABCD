package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public class JsonFormat extends AbstractOutputFormat {
    @Override
    public void output(final CloneSet cloneSet) {

    }

    @Override
    protected String getExtension() {
        return ".json";
    }
}
