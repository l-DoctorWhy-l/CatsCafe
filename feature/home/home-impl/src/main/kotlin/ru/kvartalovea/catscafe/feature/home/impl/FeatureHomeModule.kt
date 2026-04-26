package ru.kvartalovea.catscafe.feature.home.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider

val featureHomeModule = module {
    singleOf(::HomeScreenProvider) { bind<ScreenProvider>() }
}
