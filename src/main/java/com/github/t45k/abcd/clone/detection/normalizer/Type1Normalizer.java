package com.github.t45k.abcd.clone.detection.normalizer;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class Type1Normalizer implements CodeFragmentNormalizer {
    @Override
    public String normalize(final ASTNode codeFragment) throws InvalidInputException {
        final IScanner scanner = ToolFactory.createScanner(false, false, true, false);
        scanner.setSource(codeFragment.toString().toCharArray());

        final List<String> tokenList = new ArrayList<>();
        while (true) {
            final int tokenType = scanner.getNextToken();
            if (tokenType == ITerminalSymbols.TokenNameEOF) {
                break;
            }

            tokenList.add(getToken(scanner, tokenType));
        }

        return String.join(" ", tokenList);
    }

    private String getToken(final IScanner scanner, final int tokenType) {
        return new String(scanner.getCurrentTokenSource());
    }
}
