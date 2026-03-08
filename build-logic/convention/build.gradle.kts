plugins {
    `kotlin-dsl`
}

group = "com.nikitakrapo.progressif.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("multiplatformModule") {
            id = "progressif.multiplatform.module"
            implementationClass = "com.nikitakrapo.progressif.convention.MultiplatformConventionPlugin"
        }
    }
}
