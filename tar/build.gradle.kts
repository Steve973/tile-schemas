plugins {
    base
}

tasks {
    val schemaVersion = project.parent?.version.toString().substringBeforeLast(".0")
    val schemaSource = file("${rootDir}/docs/schemas/v$schemaVersion")
    val tarRoot = layout.buildDirectory.get().asFile.resolve("tarball")

    val prepareTarball by registering(Copy::class) {
        destinationDir = tarRoot

        from(schemaSource) {
            into("schemas/v$schemaVersion")
        }

        from(rootProject.file("README.md"))
        from(rootProject.file("LICENSE"))
    }

    val buildSchemaTarball by registering(Tar::class) {
        dependsOn(prepareTarball)

        archiveBaseName.set("tile-schemas")
        archiveVersion.set(project.parent?.version.toString())
        archiveExtension.set("tar.gz")
        destinationDirectory.set(layout.buildDirectory.dir("distributions"))

        compression = Compression.GZIP
        from(tarRoot)
    }

    // Wire into normal `gradle build`
    named("build") {
        dependsOn(buildSchemaTarball)
    }
}
