package com.nikitakrapo.progressif.convention

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.kotlin.multiplatform.library")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure(KotlinMultiplatformExtension::class.java) {
                jvmToolchain(17)

                targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
                    val compileSdkStr = libs.findVersion("android-compileSdk").get().requiredVersion
                    val minSdkStr = libs.findVersion("android-minSdk").get().requiredVersion
                    
                    compileSdk = compileSdkStr.toInt()
                    minSdk = minSdkStr.toInt()
                }
            }
        }
    }
}
