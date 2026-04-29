plugins {
    id("cats.android.library")
}

android {
    namespace = "ru.kvartalovea.catscafe.core.account.api"
}

dependencies {
    api(projects.common.commonUtils)
}
