package com.github.t45k.abcd.clone.detection;

public enum DetectionMode {
    TYPE1,
    TYPE2,
    ORIGINAL;

    public static DetectionMode getModeFromCLI(final String modeString) {
        switch (modeString) {
            case "1":
                return DetectionMode.TYPE1;
            case "2":
                return DetectionMode.TYPE2;
            case "3":
                return DetectionMode.ORIGINAL;
        }

        throw new RuntimeException("invalid mode was specified");
    }
}
