@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.firebase.auth"
    }

    iosArm64()
    iosSimulatorArm64()

    compilerOptions {
        freeCompilerArgs.add("-Xexplicit-backing-fields")
    }

    swiftPMDependencies {
        swiftPackage(
            url = url("https://github.com/firebase/firebase-ios-sdk.git"),
            version = from(libs.versions.firebase.ios.get()),
            products = listOf(
                product("FirebaseAuth")
            ),
        )
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.api)
            implementation(libs.kotlin.coroutines)
            implementation(projects.features.common)
            implementation(projects.features.kmpCommon)
        }

        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.auth)
        }
    }
}

