plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.ui-common"
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.models)
            implementation(projects.features.strings)
        }
    }
}
