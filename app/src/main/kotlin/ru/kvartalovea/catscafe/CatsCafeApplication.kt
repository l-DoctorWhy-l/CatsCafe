package ru.kvartalovea.catscafe

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.kvartalovea.catscafe.core.navigation.impl.coreNavigationModule
import ru.kvartalovea.catscafe.di.appModule

class CatsCafeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@CatsCafeApplication)
            modules(
                coreNavigationModule,
                appModule,
            )
        }
    }
}
