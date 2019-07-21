package com.github.t45k.abcd.clone.detection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DetectionModeTest {
    @Test
    public void testGetMode() {
        assertThat(DetectionMode.getModeFromCLI("1")).isEqualTo(DetectionMode.TYPE1);
        assertThat(DetectionMode.getModeFromCLI("2")).isEqualTo(DetectionMode.TYPE2);
        assertThat(DetectionMode.getModeFromCLI("3")).isEqualTo(DetectionMode.ORIGINAL);

        assertThatThrownBy(() -> DetectionMode.getModeFromCLI(""))
                .isInstanceOfSatisfying(RuntimeException.class, e -> assertThat(e.getMessage()).isEqualTo("invalid mode was specified"));
    }
}
