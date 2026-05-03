package ru.kvartalovea.catscafe.feature.splash.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider

val featureSplashModule = module {
    singleOf(::SplashScreenProvider) { bind<ScreenProvider>() }
}
