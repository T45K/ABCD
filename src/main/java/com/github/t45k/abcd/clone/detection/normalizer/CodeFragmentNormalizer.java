package com.github.t45k.abcd.clone.detection.normalizer;

import com.github.t45k.abcd.clone.detection.DetectionMode;
import org.eclipse.jdt.core.dom.ASTNode;

public interface CodeFragmentNormalizer {
    String normalize(final ASTNode codeFragment);

    public static CodeFragmentNormalizer create(final DetectionMode mode) {
        switch (mode) {
            case TYPE1:
                return new Type1Normalizer();
            case TYPE2:
                return new Type2Normalizer();
            case ORIGINAL:
                return new OriginalNormalizer();
        }

        throw new RuntimeException("You could not come here");
    }
}
