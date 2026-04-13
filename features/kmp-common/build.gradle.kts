plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.kmp.common"
    }
    
    sourceSets {
        commonMain.dependencies {
        }
    }
}
