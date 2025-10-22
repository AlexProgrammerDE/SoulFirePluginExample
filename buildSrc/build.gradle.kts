plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("com.gradleup.shadow:com.gradleup.shadow.gradle.plugin:9.2.2")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:8.0.0")
}
