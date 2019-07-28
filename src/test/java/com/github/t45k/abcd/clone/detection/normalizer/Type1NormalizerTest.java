package com.github.t45k.abcd.clone.detection.normalizer;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ConfigTest;
import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.ast.constructor.ASTConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class Type1NormalizerTest {
    @Test
    public void testNormalization() throws InvalidInputException {
        final CodeFragmentNormalizer normalizer = new Type1Normalizer();
        final String normalizedFragment = normalizer.normalize(getCompilationUnit());
        assertThat(normalizedFragment).isEqualTo("package normalizer ; public class Tokens { private static void main ( ) { int a = 0 ; assert a ; } }");
    }

    private CompilationUnit getCompilationUnit() {
        final Path path = Paths.get("sample/cloneDetectionSample/src/normalizer");
        final Config config = new ConfigTest().getSrcDirConfig(path.toString());
        final ASTConstructor astConstructor = ASTConstructor.create(config);
        final Set<FileAST> fileASTS = astConstructor.constructFileAST(path);
        final FileAST fileAST = fileASTS.iterator().next();
        return fileAST.getUnit();
    }
}
