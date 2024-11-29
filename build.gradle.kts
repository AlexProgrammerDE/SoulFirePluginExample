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

// libs are declared in gradle/libs.versions.toml
dependencies {
    // To use the SoulFire API
    compileOnly(libs.soulfire.api)

    // IMPORTANT: So that your plugin can be loaded by SoulFire
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
        attributes["Plugin-Requires"] = "1.15.0"

        // If you change the license of this example
        // Update this to the new license
        attributes["Plugin-License"] = "MIT"

        // Change this to the website of your plugin
        attributes["Plugin-Website"] = "https://github.com/AlexProgrammerDE/SoulFirePluginExample"
    }
}
