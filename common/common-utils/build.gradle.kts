plugins {
    id("cats.android.library")
}

android {
    namespace = "ru.kvartalovea.catscafe.common.utils"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
