enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.github.johnrengelman.shadow") version "8.1.1"
        id("org.cadixdev.licenser") version "0.6.1"
        id("net.kyori.indra") version "3.2.0"
        id("net.kyori.indra.git") version "3.2.0"
        id("net.kyori.blossom") version "2.1.0"
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven("https://central.sonatype.com/repository/maven-snapshots/") {
            name = "Sonatype Repository"
        }
        maven("https://repo.codemc.org/repository/maven-public/") {
            name = "CodeMC Repository"
        }
        maven("https://repo.pistonmaster.net/releases") {
            name = "PistonDev Release Repository"
        }
        maven("https://repo.pistonmaster.net/snapshots") {
            name = "PistonDev Snapshots Repository"
        }
        maven("https://repo.pistonmaster.net/extras") {
            name = "PistonDev Extras Repository"
        }
    }
    versionCatalogs {
        create("libs") {
            file("gradle/libs.versions.toml")
        }
    }
}

rootProject.name = "soulfire-plugin-example"
