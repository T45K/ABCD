package com.github.t45k.abcd.clone.detection.normalizer;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.ast.constructor.ASTConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class Type2NormalizerTest {
    @Test
    public void testNormalization() throws InvalidInputException {
        final CodeFragmentNormalizer normalizer = new Type2Normalizer();
        final String normalizedFragment = normalizer.normalize(getCompilationUnit());
        assertThat(normalizedFragment).isEqualTo("package $ ; public class $ { private static void $ ( ) { int $ = $ ; assert $ ; } }");
    }

    private CompilationUnit getCompilationUnit() {
        final Path path = Paths.get("sample/cloneDetectionSample/src/normalizer");
        final ASTConstructor astConstructor = ASTConstructor.create(path);
        final Set<FileAST> fileASTS = astConstructor.constructFileAST(path);
        final FileAST fileAST = fileASTS.iterator().next();
        return fileAST.getUnit();
    }
}
