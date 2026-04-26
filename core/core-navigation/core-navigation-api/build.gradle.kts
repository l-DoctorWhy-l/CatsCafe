plugins {
    id("cats.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.kvartalovea.catscafe.core.navigation.api"
}

dependencies {
    api(projects.common.commonUtils)

    api(libs.androidx.navigation.compose)
    api(libs.kotlinx.serialization.json)
}
