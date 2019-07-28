/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.t45k.abcd;

import com.github.t45k.abcd.ast.FileAST;
import com.github.t45k.abcd.ast.constructor.ASTConstructor;
import com.github.t45k.abcd.clone.detection.CloneDetection;

import java.util.Set;

public class App {
    public static void main(final String[] args) {
        final Config config = Config.Builder.buildFromCmdLineArgs(args);
        final ASTConstructor astConstructor = ASTConstructor.create(config);
        final Set<FileAST> fileASTs = astConstructor.constructFileAST(config.getSrcDir());
    }
}
