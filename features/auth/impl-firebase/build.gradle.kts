plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.auth.impl.firebase"
    }
    
    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.api)
            implementation(libs.kotlin.coroutines)
            implementation(libs.gitlive.firebase.auth)
        }
    }
}
