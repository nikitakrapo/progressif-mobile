plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.common"
    }
    
    sourceSets {
        commonMain.dependencies {
        }
    }
}
