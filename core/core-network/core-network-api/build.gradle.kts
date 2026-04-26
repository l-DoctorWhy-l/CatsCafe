plugins {
    id("cats.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.kvartalovea.catscafe.core.network.api"
}

dependencies {
    api(projects.common.commonUtils)

    api(libs.retrofit)
    api(libs.kotlinx.serialization.json)
}
