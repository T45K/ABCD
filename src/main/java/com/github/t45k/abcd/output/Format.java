package com.github.t45k.abcd.output;

public enum Format {
    JSON,
    CSV,
    XML,
    TXT;

    public static Format getFormat(final String format) {
        switch (format) {
            case "json":
                return Format.JSON;
            case "csv":
                return Format.CSV;
            case "xml":
                return Format.XML;
            case "txt":
                return Format.TXT;
        }

        throw new RuntimeException("invalid format was specified");
    }
}
