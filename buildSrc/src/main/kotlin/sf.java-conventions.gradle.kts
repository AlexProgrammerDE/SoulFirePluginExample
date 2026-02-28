import net.ltgt.gradle.errorprone.errorprone

plugins {
    `java-library`
    `maven-publish`
    id("sf.license-conventions")
    id("net.kyori.indra.git")
    id("net.ltgt.errorprone")
    id("com.github.spotbugs")
    id("org.openrewrite.rewrite")
}

rewrite {
    activeRecipe("org.openrewrite.staticanalysis.CommonStaticAnalysis")
    activeRecipe("org.openrewrite.staticanalysis.CodeCleanup")
    activeRecipe("org.openrewrite.staticanalysis.JavaApiBestPractices")
    activeRecipe("org.openrewrite.java.testing.junit5.JUnit5BestPractices")
    activeRecipe("org.openrewrite.java.testing.cleanup.BestPractices")
    activeRecipe("org.openrewrite.java.migrate.UpgradeToJava25")
    isExportDatatables = true
}

spotbugs {
    ignoreFailures = true
}

dependencies {
    errorprone("com.google.errorprone:error_prone_core:2.48.0")
    spotbugs("com.github.spotbugs:spotbugs:4.9.8")

    rewrite("org.openrewrite.recipe:rewrite-static-analysis:2.28.0")
    rewrite("org.openrewrite.recipe:rewrite-migrate-java:3.28.0")
    rewrite("org.openrewrite.recipe:rewrite-rewrite:0.21.0")
}

tasks {
    // Variable replacements
    processResources {
        filesMatching(listOf("fabric.mod.json")) {
            expand(
                mapOf(
                    "version" to project.version,
                    "description" to project.description,
                    "commit" to (indraGit.commit().orNull?.name ?: "unknown"),
                    "branch" to (indraGit.branchName().orNull ?: "unknown"),
                )
            )
        }
    }
    compileJava {
        options.errorprone {
            disableWarningsInGeneratedCode = true
        }
        options.encoding = Charsets.UTF_8.name()
        options.compilerArgs.addAll(
            listOf(
                "-parameters",
                "-nowarn",
                "-Xlint:-unchecked",
                "-Xlint:-deprecation",
                "-Xlint:-processing"
            )
        )
        options.isFork = true
    }
    test {
        reports.junitXml.required = true
        reports.html.required = true
        useJUnitPlatform()
        maxParallelForks = Runtime.getRuntime().availableProcessors().div(2).coerceAtLeast(1)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:unchecked"))
}
