plugins {
    id("progressif.multiplatform.module")
    kotlin("native.cocoapods")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.firebase.auth"
    }

    iosArm64()
    iosSimulatorArm64()

    compilerOptions {
        freeCompilerArgs.add("-Xexplicit-backing-fields")
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.api)
            implementation(libs.kotlin.coroutines)
            implementation(projects.features.common)
            implementation(projects.features.kmpCommon)
        }

        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.auth)
        }

        // TODO https://kotlinlang.org/docs/multiplatform/multiplatform-spm-import.html#configure-the-build: migrate to SPM
        cocoapods {
            name = "AuthImplFirebase"
            version = "1.0"

            ios.deploymentTarget = "14.1"

            pod("FirebaseAuth") {
                version = "~> 12.12.0"
            }
        }
    }
}

