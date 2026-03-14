plugins {
    id("progressif.multiplatform.module")
    id("progressif.multiplatform.compose")
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.nikitakrapo.progressf.strings"
    generateResClass = auto
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.strings"
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.components.resources)
        }
    }
}
