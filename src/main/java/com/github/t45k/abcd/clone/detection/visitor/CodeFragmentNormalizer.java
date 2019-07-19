package com.github.t45k.abcd.clone.detection.visitor;

import org.eclipse.jdt.core.dom.Statement;

public interface CodeFragmentNormalizer {
    String normalize(final Statement codeFragment);
}
