package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public abstract class AbstractOutputFormat implements OutputFormat {
    protected abstract String getExtension();
}
