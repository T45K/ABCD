package com.github.t45k.abcd.output;

import com.github.t45k.abcd.clone.entity.CloneSet;

public interface OutputFormat {
    void output(CloneSet cloneSet);

    public static OutputFormat create(final Format format) {
        switch (format) {
            case JSON:
                return new JsonFormat();
            case CSV:
                return new CSVFormat();
            case TXT:
                return new TextFormat();
            case XML:
                return new XMLFormat();
        }

        throw new RuntimeException("You could not come here");
    }
}
