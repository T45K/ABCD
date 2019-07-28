package com.github.t45k.abcd;

import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.output.Format;
import org.junit.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ConfigTest {

    @Test
    public void testConfig() {
        final String[] args = {"-s", ".", "-d", "3", "-o", "./output", "-f", "txt", "-b", ".", "-l", ".", "-tl", "5", "-tt", "20"};
        final Config config = Config.Builder.buildFromCmdLineArgs(args);

        assertThat(config.getSrcDir()).isEqualByComparingTo(Paths.get("."));
        assertThat(config.getDetectionMode()).isEqualByComparingTo(DetectionMode.ORIGINAL);
        assertThat(config.getOutputFile()).isEqualByComparingTo(Paths.get("./output"));
        assertThat(config.getFormat()).isEqualByComparingTo(Format.TXT);
        assertThat(config.getBinaryDir()).isEqualByComparingTo(Paths.get("."));
        assertThat(config.getLibDir()).isEqualByComparingTo(Paths.get("."));
        assertThat(config.getThresholdLine()).isEqualByComparingTo(5);
        assertThat(config.getThresholdToken()).isEqualByComparingTo(20);
    }

    @Test
    public void testError() {
        final String[] args = {"-p"};
        assertThatThrownBy(() -> Config.Builder.buildFromCmdLineArgs(args))
                .isInstanceOfSatisfying(RuntimeException.class, e -> {
                });
    }

    public Config getThresholdConfig(final int thresholdLine, final int thresholdToken) {
        final String[] args = {"-s", ".", "-d", "1", "-o", "./output", "-f", "txt", "-b", ".", "-l", ".", "-tl", Integer.toString(thresholdLine), "-tt", Integer.toString(thresholdToken)};
        return Config.Builder.buildFromCmdLineArgs(args);
    }

    public Config getBinaryAndLibConfig(final String binaryPath, final String libPath) {
        final String[] args = {"-s", ".", "-d", "3", "-o", "./output", "-f", "txt", "-b", binaryPath, "-l", libPath, "-tl", "0", "-tt", "0"};
        return Config.Builder.buildFromCmdLineArgs(args);
    }

    public Config getNoBinaryAndLibConfig() {
        final String[] args = {"-s", ".", "-d", "3", "-o", "./output", "-f", "txt", "-tl", "0", "-tt", "0"};
        return Config.Builder.buildFromCmdLineArgs(args);
    }

    public Config getSrcDirConfig(final String srcDir) {
        final String[] args = {"-s", srcDir, "-d", "3", "-o", "./output", "-f", "txt", "-tl", "0", "-tt", "0"};
        return Config.Builder.buildFromCmdLineArgs(args);
    }

    public Config getStandardConfig() {
        final String[] args = {"-s", ".", "-d", "1", "-o", "./output", "-f", "txt", "-b", "-tl", "0", "-tt", "0"};
        return Config.Builder.buildFromCmdLineArgs(args);
    }

    public Config getBindingConfig(final String srcDir, final String binaryPath, final String libPath) {
        final String[] args = {"-s", srcDir, "-d", "1", "-o", "./output", "-f", "txt", "-b", binaryPath, "-l", libPath, "-tl", "0", "-tt", "0"};
        return Config.Builder.buildFromCmdLineArgs(args);
    }
}
