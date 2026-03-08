package com.nikitakrapo.progressif.convention

import com.nikitakrapo.progressif.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.composeMultiplatform.get().pluginId)
                apply(libs.plugins.composeCompiler.get().pluginId)
            }

            extensions.configure(KotlinMultiplatformExtension::class.java) {

                sourceSets {
                    commonMain.dependencies {
                        implementation(libs.compose.runtime)
                        implementation(libs.compose.foundation)
                        implementation(libs.compose.ui)
                        implementation(libs.compose.uiToolingPreview)
                    }
                }
            }
        }
    }
}