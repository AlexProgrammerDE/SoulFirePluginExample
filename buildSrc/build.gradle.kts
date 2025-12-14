plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://maven.fabricmc.net/") {
        name = "Fabric"
    }
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:8.0.0")
    implementation("net.fabricmc:fabric-loom:1.14-SNAPSHOT")
    implementation("org.ow2.asm:asm:9.9")
    implementation("net.kyori:indra-git:4.0.0")
}
