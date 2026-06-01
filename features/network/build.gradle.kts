plugins {
    id("progressif.multiplatform.module")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.network"
    }
    
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.ktor)
            api(projects.features.common)
            implementation(projects.features.locale)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
