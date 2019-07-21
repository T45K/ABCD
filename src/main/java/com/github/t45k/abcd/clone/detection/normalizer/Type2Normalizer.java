package com.github.t45k.abcd.clone.detection.normalizer;

import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;

public class Type2Normalizer extends Type1Normalizer {
    @Override
    protected String getToken(final IScanner scanner, final int tokenType) {
        if(isIdentifier(tokenType)){
            return "$";
        }

        return new String(scanner.getCurrentTokenSource());
    }

    private boolean isIdentifier(final int tokenType){
        switch (tokenType){
            case ITerminalSymbols.TokenNameIdentifier:
            case ITerminalSymbols.TokenNameCharacterLiteral:
            case ITerminalSymbols.TokenNameDoubleLiteral:
            case ITerminalSymbols.TokenNameIntegerLiteral:
            case ITerminalSymbols.TokenNameLongLiteral:
            case ITerminalSymbols.TokenNameStringLiteral:
            case ITerminalSymbols.TokenNameFloatingPointLiteral:
                return true;
        }

        return false;
    }
}
