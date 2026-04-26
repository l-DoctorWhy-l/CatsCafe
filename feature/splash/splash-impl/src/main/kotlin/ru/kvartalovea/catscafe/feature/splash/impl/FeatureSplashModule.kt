package ru.kvartalovea.catscafe.feature.splash.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider

/**
 * Koin-модуль фичи Splash.
 *
 * TODO: добавить `viewModelOf(::SplashViewModel)` и use-case'ы определения
 * стартового маршрута, когда появится логика авторизации.
 */
val featureSplashModule = module {
    singleOf(::SplashScreenProvider) { bind<ScreenProvider>() }
}
