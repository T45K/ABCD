package com.github.t45k.abcd;

import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.output.Format;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

class Config {
    private final Path srcDir;
    private final DetectionMode detectionMode;
    private final Path outputFile;
    private final Format format;
    private final Path binaryDir;
    private final Path libDir;
    private final int thresholdLine;
    private final int thresholdToken;

    private static final DetectionMode DEFAULT_DETECTION_MODE = DetectionMode.TYPE2;
    private static final Path DEFAULT_OUTPUT_PATH = Paths.get(".", "output");
    private static final Format DEFAULT_FORMAT = Format.TXT;
    private static final int DEFAULT_THRESHOLD_LINE = 5;
    private static final int DEFAULT_THRESHOLD_TOKEN = 20;

    private Config(final Builder builder) {
        this.srcDir = builder.srcDir;
        this.detectionMode = builder.detectionMode;
        this.outputFile = builder.outputFile;
        this.format = builder.format;
        this.binaryDir = builder.binaryDir;
        this.libDir = builder.libDir;
        this.thresholdLine = builder.thresholdLine;
        this.thresholdToken = builder.thresholdToken;
    }

    public Path getSrcDir() {
        return srcDir;
    }

    public DetectionMode getDetectionMode() {
        return detectionMode;
    }

    public Path getOutputFile() {
        return outputFile;
    }

    public Format getFormat() {
        return format;
    }

    public Path getBinaryDir() {
        return binaryDir;
    }

    public Path getLibDir() {
        return libDir;
    }

    public int getThresholdLine() {
        return thresholdLine;
    }

    public int getThresholdToken() {
        return thresholdToken;
    }

    static class Builder {
        private Path srcDir;
        private DetectionMode detectionMode = DEFAULT_DETECTION_MODE;
        private Path outputFile = DEFAULT_OUTPUT_PATH;
        private Format format = DEFAULT_FORMAT;
        private Path binaryDir;
        private Path libDir;
        private int thresholdLine = DEFAULT_THRESHOLD_LINE;
        private int thresholdToken = DEFAULT_THRESHOLD_TOKEN;

        private static Logger logger = LoggerFactory.getLogger(Builder.class);

        static Config buildFromCmdLineArgs(final String[] args) {
            final Builder builder = new Builder();
            final CmdLineParser cmdLineParser = new CmdLineParser(builder);

            try {
                cmdLineParser.parseArgument(args);
            } catch (CmdLineException e) {
                logger.error("Invalid argument was specified", e);
                cmdLineParser.printUsage(System.out);
                System.exit(1);
            }

            return builder.build();
        }

        Config build() {
            return new Config(this);
        }

        @Option(name = "-s", aliases = "--src-dir", usage = "Directory path of target source files", required = true)
        private void setSrcDir(final String srcDir) {
            this.srcDir = Paths.get(srcDir);
        }

        @Option(name = "-d", aliases = "--detection-mode", usage = "Clone type which you want to detect\n1: Type1\n2: Type2\n3: Original")
        private void setDetectionMode(final String detectionMode) {
            this.detectionMode = DetectionMode.getModeFromCLI(detectionMode);
        }

        @Option(name = "-o", aliases = "--output-file", usage = "Result output file path")
        private void setOutputFile(final String outputFile) {
            this.outputFile = Paths.get(outputFile);
        }

        @Option(name = "-f", aliases = "--format", usage = "Output file format\njson, csv, xml, or txt")
        private void setFormat(final String format) {
            this.format = Format.getFormat(format);
        }

        @Option(name = "-b", aliases = "--binary-dir", usage = "Directory of binary files")
        private void setBinaryDir(final String binaryDir) {
            this.binaryDir = Paths.get(binaryDir);
        }

        @Option(name = "-l", aliases = "--lib-dir", usage = "Directory of libraries")
        private void setLibDir(final String libDir) {
            this.libDir = Paths.get(libDir);
        }

        @Option(name = "-tl", aliases = "--threshold-line", usage = "Threshold line")
        private void setThresholdLine(final String thresholdLine) {
            this.thresholdLine = Integer.parseInt(thresholdLine);
        }

        @Option(name = "-tt", aliases = "--threshold-token", usage = "Threshold token")
        private void setThresholdToken(final String thresholdToken) {
            this.thresholdToken = Integer.parseInt(thresholdToken);
        }
    }
}
