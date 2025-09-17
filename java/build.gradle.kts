plugins {
    java
}

group = "org.storck.tile-schemas"
//name = "tile-schemas"
description = "Provides all schemas for tiles."

sourceSets {
    main {
        resources {
            srcDirs(
                "${rootDir}/docs")
        }
    }
    test {
        java {
            srcDirs("java/src/test/java")
        }
        resources {
            srcDirs("java/src/test/resources")
        }
    }
}

dependencies {
    testImplementation("com.github.erosb:everit-json-schema:1.14.6")
    testImplementation("org.json:json:20250517")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.13.4")
}

tasks {
    val schemaParentDir = file("${rootDir}/docs/schemas")
    val schemaVersion = rootProject.version.toString().substringBeforeLast(".0")

    named<ProcessResources>("processResources") {
        from(schemaParentDir) {
            include("$schemaVersion/**")
            into("schemas")
        }

        into("META-INF") {
            from(rootProject.file("LICENSE"))
            from(rootProject.file("README.md"))
        }
    }

    named<ProcessResources>("processTestResources") {
        from(schemaParentDir) {
            into("schemas")
        }
    }

    jar {
        archiveBaseName.set("tile-schemas")
    }

    test {
        useJUnitPlatform()
    }
}