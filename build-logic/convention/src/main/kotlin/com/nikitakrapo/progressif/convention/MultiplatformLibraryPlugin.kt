package com.nikitakrapo.progressif.convention

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.nikitakrapo.progressif.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.kotlinMultiplatform.get().pluginId)
                apply(libs.plugins.androidMultiplatformLibrary.get().pluginId)
            }

            extensions.configure(KotlinMultiplatformExtension::class.java) {
                iosArm64()
                iosSimulatorArm64()

                jvmToolchain(17)

                sourceSets.getByName("commonMain").dependencies {
                    implementation(libs.napier)
                }

                targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
                    val compileSdkStr = libs.versions.android.compileSdk.get()
                    val minSdkStr = libs.versions.android.minSdk.get()

                    compileSdk = compileSdkStr.toInt()
                    minSdk = minSdkStr.toInt()
                }

                compilerOptions.freeCompilerArgs.add("-Xskip-prerelease-check")
            }
        }
    }
}