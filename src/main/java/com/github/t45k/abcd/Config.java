package com.github.t45k.abcd;

import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.output.Format;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private final Path srcDir;
    private final DetectionMode detectionMode;
    private final Path outputFile;
    private final Format format;
    private final Path binaryDir;
    private final Path libDir;
    private final int thresholdLine;
    private final int thresholdToken;

    private static final DetectionMode DEFAULT_DETECTION_MODE = DetectionMode.TYPE2;
    private static final Path DEFAULT_OUTPUT_PATH = Paths.get(".","output");
    private static final Format DEFAULT_FORMAT = Format.TXT;
    private static final int DEAFULT_THRESHOLD_LINE = 5;
    private static final int DEAFULT_THRESHOLD_TOKEN = 20;
}
