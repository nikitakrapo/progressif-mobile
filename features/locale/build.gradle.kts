plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.locale"
    }

    sourceSets {
        commonMain.dependencies {
        }
    }
}
