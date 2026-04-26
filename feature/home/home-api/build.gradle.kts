plugins {
    id("cats.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.kvartalovea.catscafe.feature.home.api"
}

dependencies {
    api(libs.kotlinx.serialization.json)
}
