plugins {
    `java-library`
    `maven-publish`
    id("sf.license-conventions")
}

java.javaTarget(17)

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:unchecked"))
}
