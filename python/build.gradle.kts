plugins {
    base
}

tasks {
    val schemaVersion = project.parent?.version.toString().substringBeforeLast(".0")
    val pythonTemplateDir = file("wheel-template")
    val outputDir = layout.buildDirectory.get().asFile

    register<Copy>("preparePythonWheel") {
        // Copy and filter template files
        from(pythonTemplateDir) {
            include("**")
            filteringCharset = "UTF-8"
            expand("version" to schemaVersion)
        }

        into(outputDir)

        doLast {
            // Copy schema files into the correct place
            val schemaSource = file("${rootDir}/docs/schemas")
            val schemaDest = outputDir.resolve("tile_schemas/schemas")

            schemaDest.mkdirs()

            copy {
                from(schemaSource)
                include("v$schemaVersion/**")
                into(schemaDest)
            }

            val readmeTarget = outputDir.resolve("tile_schemas")
            copy {
                from(rootProject.file("README.md"))
                into(readmeTarget)
            }
        }
    }

    register<Exec>("buildPythonWheel") {
        dependsOn("preparePythonWheel")

        val logFile = layout.buildDirectory.get().asFile.resolve("python-build.log")

        workingDir = layout.buildDirectory.get().asFile
        commandLine("bash", "-c", "python3 -m build --wheel > ${logFile.absolutePath} 2>&1")
    }

    named<DefaultTask>("build") {
        dependsOn("buildPythonWheel")
    }
}
