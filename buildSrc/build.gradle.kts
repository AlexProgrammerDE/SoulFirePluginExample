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
    implementation("com.diffplug.spotless:spotless-plugin-gradle:8.2.1")
    implementation("net.fabricmc:fabric-loom:1.15.3")
    implementation("org.ow2.asm:asm:9.9.1")
    implementation("net.kyori:indra-git:4.0.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

kotlin {
    jvmToolchain(25)
}
