plugins {
    id("sf.java-conventions")
    idea
    id("fabric-loom")
}

allprojects {
    group = "com.soulfiremc"
    version = "1.0.0-SNAPSHOT"
    description = "Example of how to make a plugin for SoulFire."
    repositories {
        maven("https://repo.viaversion.com") {
            name = "ViaVersion Repository"
            content {
                includeGroup("com.viaversion")
                includeGroup("com.viaversion.mcstructs")
                includeGroup("net.raphimc")
            }
        }
        maven("https://jitpack.io") {
            name = "Jitpack Repository"
            content {
                includeGroupByRegex("com\\.github\\..*")
            }
        }
        maven("https://libraries.minecraft.net") {
            name = "Minecraft Repository"
            content {
                includeGroup("net.minecraft")
                includeGroup("com.mojang")
            }
        }
        maven("https://nexus.lucko.me/repository/hosted/") {
            name = "Lucko Nexus"
            content {
                includeGroup("me.lucko")
                includeModule("net.kyori", "adventure-text-feature-pagination")
            }
        }
        maven("https://maven.parchmentmc.org") {
            name = "ParchmentMC"
            content {
                includeGroup("org.parchmentmc.data")
            }
        }
        maven("https://maven.fabricmc.net") {
            name = "FabricMC"
            content {
                includeGroup("net.fabricmc")
            }
        }
        ivy("https://github.com/3arthqu4ke") {
            patternLayout {
                artifact("/[organisation]/releases/download/[revision]/[artifact]-[revision](-[classifier])(.[ext])")
            }
            metadataSources {
                artifact()
            }
            content {
                includeGroup("headlessmc")
            }
        }
        maven("https://central.sonatype.com/repository/maven-snapshots/") {
            name = "Sonatype Snapshot Repository"
            mavenContent { snapshotsOnly() }
        }
        maven("https://maven.lenni0451.net/everything") {
            name = "Lenni0451 Repository"
            content {
                includeGroup("net.raphimc")
            }
        }
        maven("https://repo.opencollab.dev/maven-snapshots") {
            name = "OpenCollab Snapshot Repository"
            content {
                includeGroup("org.cloudburstmc.netty")
            }
        }
        maven("https://repo.codemc.org/repository/maven-public/") {
            name = "CodeMC Repository"
            content {
                includeGroup("com.soulfiremc")
            }
        }
        mavenCentral()
    }
}

loom {
    accessWidenerPath = file("src/main/resources/soulfire-plugin-example.accesswidener")

    // Disable run configurations as this is a plugin, not a standalone mod
    runs {
        removeIf { true }
    }
}

// libs are declared in gradle/libs.versions.toml
dependencies {
    minecraft(libs.minecraft)
    @Suppress("UnstableApiUsage")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.parchmentMc.get()}:${libs.versions.parchment.get()}@zip")
    })
    modImplementation(libs.fabric.loader)

    modImplementation(libs.soulfire.mod)
    compileOnly(libs.soulfire.shared)

    // For code generation
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
