plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.models"
    }
    
    sourceSets {
        commonMain.dependencies {
        }
    }
}
