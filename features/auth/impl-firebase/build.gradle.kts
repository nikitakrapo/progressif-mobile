plugins {
    id("progressif.multiplatform.module")
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
            implementation(projects.features.common)
            implementation(projects.features.firebase.auth)
        }
    }
}

