

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.nikitakrapo.progressif"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.nikitakrapo.progressif"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    //noinspection WrongGradleMethod
    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xskip-prerelease-check")
        }
    }
}

dependencies {
    implementation(projects.integrationCore)
    implementation(projects.features.firebase.auth)
    implementation(projects.features.kmpCommon)
    implementation(libs.decompose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.uiToolingPreview)
    implementation(libs.napier)
    debugImplementation(libs.compose.uiTooling)
}
