plugins {
    id("cats.android.application")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.kvartalovea.catscafe"

    defaultConfig {
        applicationId = "ru.kvartalovea.catscafe"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.catscafe.example/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://api.catscafe.example/\"")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(projects.common.commonUi)
    implementation(projects.common.commonUtils)

    implementation(projects.core.coreNetwork.coreNetworkApi)
    implementation(projects.core.coreNetwork.coreNetworkImpl)
    implementation(projects.core.coreDatabase.coreDatabaseApi)
    implementation(projects.core.coreDatabase.coreDatabaseImpl)
    implementation(projects.core.coreNavigation.coreNavigationApi)
    implementation(projects.core.coreNavigation.coreNavigationImpl)
    implementation(projects.core.coreAccount.coreAccountApi)
    implementation(projects.core.coreAccount.coreAccountImpl)

    // feature -impl модули — подключаются только в :app (для регистрации в Koin/NavHost)
    implementation(projects.feature.splash.splashImpl)
    implementation(projects.feature.auth.authImpl)
    implementation(projects.feature.home.homeImpl)
    implementation(projects.feature.catalog.catalogImpl)
    implementation(projects.feature.booking.bookingImpl)
    implementation(projects.feature.profile.profileImpl)
    implementation(projects.feature.catDetails.catDetailsImpl)
    implementation(projects.feature.myBookings.myBookingsImpl)
    implementation(projects.feature.donations.donationsImpl)
    implementation(projects.feature.splash.splashApi)
    implementation(projects.feature.home.homeApi)
    implementation(projects.feature.catalog.catalogApi)
    implementation(projects.feature.booking.bookingApi)
    implementation(projects.feature.profile.profileApi)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
