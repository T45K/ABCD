package com.github.t45k.abcd.clone.detection.visitor;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.ast.constructor.ASTConstructor;
import com.github.t45k.abcd.clone.detection.DetectionMode;
import com.github.t45k.abcd.clone.entity.CodeFragment;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeFragmentFindingVisitorTest {
    @Test
    public void test() {
        final FileAST fileAST = getFileASTForTest();
        final Stream<CodeFragment> codeFragments = CodeFragmentFindingVisitor.findCodeFragments(DetectionMode.TYPE1, fileAST);
        final List<CodeFragment> list = codeFragments.collect(Collectors.toList());
        list.forEach(e -> System.out.println(e.toString()));
        assertThat(list.size()).isEqualTo(10);
    }

    private FileAST getFileASTForTest() {
        final Path rootPath = Paths.get("sample/cloneDetectionSample/src/visitor");
        final ASTConstructor astConstructor = ASTConstructor.create(rootPath, null, null);
        return astConstructor.constructFileAST(rootPath).iterator().next();
    }
}
