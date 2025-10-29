plugins {
    `java-library`
    `maven-publish`
    id("sf.license-conventions")
    id("net.kyori.indra.git")
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
