plugins {
    id("sw.java-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    jar {
        archiveClassifier.set("unshaded")
        from(project.rootProject.file("LICENSE"))
    }

    shadowJar {
        archiveClassifier.set("")
        mergeServiceFiles()
    }

    build {
        dependsOn(shadowJar)
    }
}
