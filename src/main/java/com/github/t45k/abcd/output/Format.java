package com.github.t45k.abcd.output;

public enum Format {
    JSON,
    CSV,
    XML,
    TXT;

    public Format getFormat(final String format) {
        switch (format) {
            case "json":
                return Format.JSON;
            case "CSV":
                return Format.CSV;
            case "XML":
                return Format.XML;
            case "TXT":
                return Format.TXT;
        }

        throw new RuntimeException("invalid format was specified");
    }
}
