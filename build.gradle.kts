plugins {
    java
    idea
    id("sf.shadow-conventions")
}

allprojects {
    group = "com.soulfiremc"
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
        attributes["Plugin-Dependencies"] = ""
        attributes["Plugin-Id"] = "soulfire-example-plugin"
        attributes["Plugin-Provider"] = "Pistonmaster"
        attributes["Plugin-Version"] = version

        // This is the version of SoulFire that this plugin requires
        // Update this when you update the version of SoulFire that this plugin requires
        attributes["Plugin-Requires"] = "1.12.2"

        // If you change the license of this example
        // Update this to the new license
        attributes["Plugin-License"] = "MIT"
    }
}
