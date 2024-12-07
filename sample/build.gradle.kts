plugins {
    id("com.android.application")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    androidTarget()
    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        androidMain {
            //noinspection UseTomlInstead
            dependencies {
                implementation("androidx.activity:activity-compose:1.9.3")
                implementation("com.google.android.material:material:1.12.0")
            }
        }
        commonMain {
            dependencies {
                implementation(projects.snowfallCompose)
                implementation(compose.material3)
            }
        }
    }

    jvmToolchain(17)
}

android {
    namespace = "io.github.skeptick.snowfall.sample"
    compileSdk = 35

    defaultConfig {
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }
}