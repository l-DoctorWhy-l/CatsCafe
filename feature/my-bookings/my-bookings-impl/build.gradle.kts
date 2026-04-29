plugins {
    id("cats.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.kvartalovea.catscafe.feature.mybookings.impl"
}

dependencies {
    implementation(projects.feature.myBookings.myBookingsApi)
    implementation(projects.common.commonUi)
    implementation(projects.common.commonUtils)
    implementation(projects.core.coreNavigation.coreNavigationApi)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}
