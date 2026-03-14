plugins {
    id("progressif.multiplatform.module")
    id("progressif.multiplatform.compose")
}


kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.design.components"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.features.design.core)
            api(projects.features.strings)
            implementation(libs.compose.components.resources)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}