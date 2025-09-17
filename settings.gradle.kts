rootProject.name = "tile-schemas"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

include("tile-schemas-java", "tile-schemas-python", "tile-schemas-tar")

project(":tile-schemas-java").projectDir = file("java")
project(":tile-schemas-python").projectDir = file("python")
project(":tile-schemas-tar").projectDir = file("tar")
