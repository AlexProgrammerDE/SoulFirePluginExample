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
    implementation("com.diffplug.spotless:spotless-plugin-gradle:8.2.0")
    implementation("net.fabricmc:fabric-loom:1.14.10")
    implementation("org.ow2.asm:asm:9.9.1")
    implementation("net.kyori:indra-git:4.0.0")
}
