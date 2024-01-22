plugins {
    java
    idea
    id("sf.shadow-conventions")
}

allprojects {
    group = "net.pistonmaster"
    version = "1.0.0-SNAPSHOT"
    description = "Example of how to make a plugin for SoulFire."
}

dependencies {
    // To use the SoulFire API
    compileOnly(libs.soulfire.api)

    // To get pf4j to find all available extensions
    annotationProcessor(libs.pf4j)

    // For code generation
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.named<Jar>("jar") {
    manifest {
        // Plugin attribute is not required, we have an annotation processor for that.
        // attributes["Plugin-Class"] = ""
        attributes["Plugin-Dependencies"] = ""
        attributes["Plugin-Id"] = "soulfire-example-plugin"
        attributes["Plugin-Provider"] = "Pistonmaster"
        attributes["Plugin-Version"] = version
    }
}
