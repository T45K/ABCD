package com.github.t45k.abcd.clone.detection.normalizer;

import com.github.t45k.abcd.clone.detection.DetectionMode;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeFragmentNormalizerTest {
    @Test
    public void testCreate() {
        final CodeFragmentNormalizer type1Normalizer = CodeFragmentNormalizer.create(DetectionMode.TYPE1);
        assertThat(type1Normalizer).isInstanceOf(Type1Normalizer.class);

        final CodeFragmentNormalizer type2Normalizer = CodeFragmentNormalizer.create(DetectionMode.TYPE2);
        assertThat(type2Normalizer).isInstanceOf(Type2Normalizer.class);

        final CodeFragmentNormalizer originalNormalizer = CodeFragmentNormalizer.create(DetectionMode.ORIGINAL);
        assertThat(originalNormalizer).isInstanceOf(OriginalNormalizer.class);
    }
}