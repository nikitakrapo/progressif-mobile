import com.nikitakrapo.progressif.gradle.libs

plugins {
    id("progressif.multiplatform.module")
}

kotlin {
    androidLibrary {
        namespace = "com.nikitakrapo.progressif.decompose_extensions"
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.decompose)
            implementation(libs.kotlin.coroutines)
        }
    }
}

