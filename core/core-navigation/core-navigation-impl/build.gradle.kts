plugins {
    id("cats.android.library.compose")
}

android {
    namespace = "ru.kvartalovea.catscafe.core.navigation.impl"
}

dependencies {
    implementation(projects.core.coreNavigation.coreNavigationApi)
    implementation(projects.common.commonUtils)

    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}
