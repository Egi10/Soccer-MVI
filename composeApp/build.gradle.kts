import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).takeIf { "XCODE_VERSION_MAJOR" in System.getenv().keys } // Export the framework only for Xcode builds
    ?.forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
            linkerOpts("-lsqlite3")

            export(libs.decompose)
            export(libs.essenty.lifecycle)
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            // Ktor
            implementation(libs.ktor.client.okhttp)
            // SQLDelight
            implementation(libs.sqldelight.android.driver)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            // Decompose
            api(libs.decompose)
            implementation(libs.decompose.extensions.compose)
            implementation(libs.essenty.lifecycle.coroutines)
            // Serialization
            implementation(libs.kotlinx.serialization.json)
            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            // Kermit
            implementation(libs.kermit)
            // Compottie
            implementation(libs.compottie)
            implementation(libs.compottie.dot)
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
            // Inspektify
            implementation(libs.inspektify.ktor3)
            // Coil
            implementation(libs.coil)
            implementation(libs.coil.network.ktor3)
            implementation(libs.coil.compose)
            // DateTime
            implementation(libs.kotlinx.datetime)
        }

        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)
            // SQLDelight
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "com.bajapuik.soccer_mvi"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.bajapuik.soccer_mvi"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
        // Koin
        implementation(project.dependencies.platform(libs.koin.bom))
        implementation(libs.koin.android)
        // Coroutines
        implementation(libs.kotlinx.coroutines.android)
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.bajapuik.soccer_mvi.resources"
    generateResClass = auto
}

sqldelight {
    databases {
        create("SoccerDatabase") {
            packageName.set("com.bajapuik.soccer_mvi.db")
        }
    }
}

