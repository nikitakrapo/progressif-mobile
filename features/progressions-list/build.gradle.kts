import com.nikitakrapo.progressif.gradle.libs

plugins {
    id("progressif.multiplatform.module")
    id("progressif.multiplatform.compose")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.progressions_list"

        androidResources.enable = true
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.design.core)
            implementation(projects.features.networkRepositories)
            implementation(projects.features.models)
            implementation(projects.features.strings)
            implementation(projects.features.uiCommon)
            implementation(libs.decompose)
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
