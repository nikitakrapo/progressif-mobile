plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.di"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.koin.core)
        }
    }
}
