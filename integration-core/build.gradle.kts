import com.nikitakrapo.progressif.gradle.libs

plugins {
    id("progressif.multiplatform.module")
    id("progressif.multiplatform.compose")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.library"
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.progressionsList)
            implementation(projects.features.tricks)
            implementation(projects.features.locale)
            implementation(projects.features.profile)
            implementation(projects.features.design.core)
            implementation(projects.features.design.components)
            implementation(projects.features.strings)
            implementation(projects.features.decomposeExtensions)
            implementation(projects.features.kmpCommon)
            implementation(projects.features.network)
            implementation(projects.features.auth.implFirebase)
            implementation(libs.multiplatformSettings)
            implementation(projects.features.auth.ui)
            implementation(projects.features.repositories)
            implementation(projects.features.di)
            implementation(libs.bundles.decompose)
            implementation(libs.decompose.extensions.compose)
            implementation(libs.bundles.mvikotlin)
            implementation(libs.compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}
