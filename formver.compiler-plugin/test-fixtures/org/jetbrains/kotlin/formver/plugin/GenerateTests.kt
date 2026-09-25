/*
 * Copyright 2010-2025 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.formver.plugin

import org.jetbrains.kotlin.formver.plugin.runners.AbstractPhasedDiagnosticTest
import org.jetbrains.kotlin.generators.dsl.junit5.generateTestGroupSuiteWithJUnit5
import java.io.File

fun main() {
    generateTestGroupSuiteWithJUnit5 {
        testGroup(testDataRoot = "formver.compiler-plugin/testData", testsRoot = "formver.compiler-plugin/test-gen") {
            testClass<AbstractPhasedDiagnosticTest> {
                model("diagnostics")
            }
        }
    }

    // The generator's unqualified String is shadowed by the nested stdlib/string test class.
    val generatedTests = File("formver.compiler-plugin/test-gen/org/jetbrains/kotlin/formver/plugin/runners/PhasedDiagnosticTestGenerated.java")
    val source = generatedTests.readText()
    generatedTests.writeText(source.replace("private void run(String fileName)", "private void run(java.lang.String fileName)"))
}
