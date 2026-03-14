plugins {
    id("progressif.multiplatform.module")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.network-repositories"
    }
    
    sourceSets {
        commonMain.dependencies {
            api(projects.features.network)
            api(projects.features.models)
            api(projects.features.common)
        }
    }
}
