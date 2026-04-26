plugins {
    id("cats.android.library.compose")
}

android {
    namespace = "ru.kvartalovea.catscafe.common.ui"
}

dependencies {
    implementation(projects.common.commonUtils)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
