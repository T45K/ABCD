package com.github.t45k.abcd.clone.detection;

import com.github.t45k.abcd.Config;
import com.github.t45k.abcd.ConfigTest;
import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.ast.constructor.ASTConstructor;
import com.github.t45k.abcd.clone.entity.CloneSet;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CloneDetectionTest {
    @Test
    public void test() {
        final Config config = new ConfigTest().getStandardConfig(0, 0);
        final CloneDetection cloneDetection = new CloneDetection(config);
        final Set<CloneSet> cloneSets = cloneDetection.detectClones(getFileASTForTest());
        assertThat(cloneSets).hasSize(1);
        final CloneSet cloneSet = cloneSets.iterator().next();
        assertThat(cloneSet.getCloneSet()).hasSize(3);
    }

    private Set<FileAST> getFileASTForTest() {
        final Path rootPath = Paths.get("sample/cloneDetectionSample/src/detection");
        final ASTConstructor astConstructor = ASTConstructor.create(rootPath);
        return astConstructor.constructFileAST(rootPath);
    }
}
