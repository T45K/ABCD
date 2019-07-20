package com.github.t45k.abcd.clone.detection.normalizer;

import org.eclipse.jdt.core.dom.ASTNode;

public class Type1Normalizer implements CodeFragmentNormalizer {
    @Override
    public String normalize(final ASTNode codeFragment) {
        // tmp
        return codeFragment.toString();
    }
}
