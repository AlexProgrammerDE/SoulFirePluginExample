plugins {
    java
    idea
    id("sw.shadow-conventions")
}

group = "net.pistonmaster"
version = "1.0.0-SNAPSHOT"
description = "Example of how to make a plugin for ServerWrecker."

dependencies {
    // To use the ServerWrecker API
    compileOnly(libs.serverwrecker.api)
    annotationProcessor(libs.pf4j)

    // For code generation
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.named<Jar>("jar") {
    manifest {
        // Plugin classes are not required, we have server extensions for that.
        // attributes["Plugin-Class"] = ""
        attributes["Plugin-Dependencies"] = ""
        attributes["Plugin-Id"] = "serverwrecker-example-plugin"
        attributes["Plugin-Provider"] = "Pistonmaster"
        attributes["Plugin-Version"] = version
    }
}
