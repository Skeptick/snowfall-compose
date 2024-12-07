@file:Suppress("UnstableApiUsage")

rootProject.name = "snowfall"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":snowfall-compose", ":sample")