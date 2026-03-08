plugins {
    id("progressif.multiplatform.module")
    id("progressif.multiplatform.compose")
}


kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.design.core"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.compose.material3)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}