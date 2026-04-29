plugins {
    id("cats.android.library")
}

android {
    namespace = "ru.kvartalovea.catscafe.core.account.impl"
}

dependencies {
    implementation(projects.core.coreAccount.coreAccountApi)
    implementation(projects.common.commonUtils)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
