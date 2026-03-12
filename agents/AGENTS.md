General instructions:
- Stick to modern Android development standards

Project instructions:
- Define all the dependencies through [libs.versions.toml](../gradle/libs.versions.toml). 
For dependencies which have multiple modules, use {baseName}-{moduleName} naming format. For core modules you may drop the -{moduleName}
- This project uses Decompose and MVIKotlin heavily. For documentation, see https://arkivanov.github.io/MVIKotlin/ and https://arkivanov.github.io/Decompose/.
- All the feature modules should be in /features module. In build.gradle.kts, use id("progressif.multiplatform.module") plugin for default KMP modules, 
also apply id("progressif.multiplatform.compose") and add implementation(projects.features.design.core) as a dependency for modules that would involve UI and will use Compose Multiplatform.