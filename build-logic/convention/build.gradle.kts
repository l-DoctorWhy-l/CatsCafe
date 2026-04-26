plugins {
    `kotlin-dsl`
}

group = "ru.kvartalovea.catscafe.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "cats.android.application"
            implementationClass = "ru.kvartalovea.catscafe.buildlogic.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "cats.android.library"
            implementationClass = "ru.kvartalovea.catscafe.buildlogic.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "cats.android.library.compose"
            implementationClass = "ru.kvartalovea.catscafe.buildlogic.AndroidLibraryComposeConventionPlugin"
        }
    }
}
