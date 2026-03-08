plugins {
    `kotlin-dsl`
}

group = "com.nikitakrapo.progressif.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)

    // https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("multiplatformModule") {
            id = "progressif.multiplatform.module"
            implementationClass = "com.nikitakrapo.progressif.convention.MultiplatformLibraryPlugin"
        }

        register("multiplatformCompose") {
            id = "progressif.multiplatform.compose"
            implementationClass = "com.nikitakrapo.progressif.convention.MultiplatformComposePlugin"
        }
    }
}
