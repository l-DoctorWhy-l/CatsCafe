package ru.kvartalovea.catscafe

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.kvartalovea.catscafe.core.account.impl.coreAccountModule
import ru.kvartalovea.catscafe.core.database.impl.coreDatabaseModule
import ru.kvartalovea.catscafe.core.navigation.impl.coreNavigationModule
import ru.kvartalovea.catscafe.core.network.impl.NetworkConfig
import ru.kvartalovea.catscafe.core.network.impl.coreNetworkModule
import ru.kvartalovea.catscafe.feature.auth.impl.featureAuthModule
import ru.kvartalovea.catscafe.feature.booking.impl.featureBookingModule
import ru.kvartalovea.catscafe.feature.catalog.impl.featureCatalogModule
import ru.kvartalovea.catscafe.feature.catdetails.impl.featureCatDetailsModule
import ru.kvartalovea.catscafe.feature.mybookings.impl.featureMyBookingsModule
import ru.kvartalovea.catscafe.feature.donations.impl.featureDonationsModule
import ru.kvartalovea.catscafe.feature.home.impl.featureHomeModule
import ru.kvartalovea.catscafe.feature.profile.impl.featureProfileModule
import ru.kvartalovea.catscafe.feature.splash.impl.featureSplashModule

class CatsCafeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@CatsCafeApplication)
            modules(
                coreNetworkModule(
                    NetworkConfig(
                        baseUrl = BuildConfig.BASE_URL,
                        isDebug = BuildConfig.DEBUG,
                    ),
                ),
                coreDatabaseModule,
                coreNavigationModule,
                coreAccountModule,
                featureSplashModule,
                featureAuthModule,
                featureHomeModule,
                featureCatalogModule,
                featureBookingModule,
                featureProfileModule,
                featureCatDetailsModule,
                featureMyBookingsModule,
                featureDonationsModule,
            )
        }
    }
}
