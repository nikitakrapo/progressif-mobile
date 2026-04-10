rootProject.name = "Progressif"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":integration-core")
include(":androidApp")

include(":features:design:core")
include(":features:design:components")
include(":features:ui-common")
include(":features:common")
include(":features:models")
include(":features:strings")
include(":features:network")
include(":features:network-repositories")
include(":features:di")
include(":features:progressions-list")
include(":features:progression-details")
include(":features:decompose-extensions")
