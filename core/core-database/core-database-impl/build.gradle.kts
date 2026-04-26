plugins {
    id("cats.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "ru.kvartalovea.catscafe.core.database.impl"
}

dependencies {
    implementation(projects.core.coreDatabase.coreDatabaseApi)
    implementation(projects.common.commonUtils)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
