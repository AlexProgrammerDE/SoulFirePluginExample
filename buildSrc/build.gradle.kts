plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://maven.wagyourtail.xyz/releases") {
        name = "WagYourReleases"
    }
    maven("https://maven.wagyourtail.xyz/snapshots") {
        name = "WagYourSnapshots"
    }
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:8.0.0")
    implementation("xyz.wagyourtail.unimined:xyz.wagyourtail.unimined.gradle.plugin:1.4.1")
    implementation("org.ow2.asm:asm:9.9")
}
