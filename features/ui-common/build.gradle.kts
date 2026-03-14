plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.ui-common"
    }
    
    sourceSets {
        commonMain.dependencies {
            api(libs.compose.components.resources)
            implementation(projects.features.models)
            implementation(projects.features.strings)
        }
    }
}
