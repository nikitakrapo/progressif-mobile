plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.auth.api"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.common)
            implementation(libs.kotlin.coroutines)
        }
    }
}
