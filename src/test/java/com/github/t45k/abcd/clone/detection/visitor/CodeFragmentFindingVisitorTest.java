package com.github.t45k.abcd.clone.detection.visitor;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ConfigTest;
import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.ast.constructor.ASTConstructor;
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
        final Config config = new ConfigTest().getThresholdConfig(0, 0);
        final Stream<CodeFragment> codeFragments = CodeFragmentFindingVisitor.findCodeFragments(fileAST, config);
        final List<CodeFragment> list = codeFragments.collect(Collectors.toList());
        assertThat(list.size()).isEqualTo(10);
    }

    @Test
    public void testThresholdLine() {
        final FileAST fileAST = getFileASTForTest();
        final Config config = new ConfigTest().getThresholdConfig(100, 0);
        final Stream<CodeFragment> codeFragments = CodeFragmentFindingVisitor.findCodeFragments(fileAST, config);
        assertThat(codeFragments).isEmpty();
    }

    @Test
    public void testThresholdToken() {
        final FileAST fileAST = getFileASTForTest();
        final Config config = new ConfigTest().getThresholdConfig(0, 110);
        final Stream<CodeFragment> codeFragments = CodeFragmentFindingVisitor.findCodeFragments(fileAST, config);
        assertThat(codeFragments).isEmpty();
    }

    private FileAST getFileASTForTest() {
        final Path rootPath = Paths.get("sample/cloneDetectionSample/src/visitor");
        final ConfigTest configTest = new ConfigTest();
        final Config srcDirConfig = configTest.getSrcDirConfig(rootPath.toString());
        final ASTConstructor astConstructor = ASTConstructor.create(srcDirConfig);
        return astConstructor.constructFileAST(rootPath).iterator().next();
    }
}
