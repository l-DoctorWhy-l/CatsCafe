plugins {
    id("cats.android.library")
}

android {
    namespace = "ru.kvartalovea.catscafe.core.database.api"
}

dependencies {
    api(projects.common.commonUtils)

    api(libs.androidx.room.runtime)
    api(libs.androidx.room.ktx)
}
