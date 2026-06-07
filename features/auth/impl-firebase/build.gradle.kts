plugins {
    id("progressif.multiplatform.module")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.auth.impl.firebase"
    }

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.api)
            implementation(libs.kotlin.coroutines)
            implementation(libs.ktor.client.core)
            implementation(libs.multiplatformSettings)
            implementation(libs.koin.core)
            implementation(projects.features.common)
            implementation(projects.features.firebase.auth)
            implementation(projects.features.network)
        }
    }
}

