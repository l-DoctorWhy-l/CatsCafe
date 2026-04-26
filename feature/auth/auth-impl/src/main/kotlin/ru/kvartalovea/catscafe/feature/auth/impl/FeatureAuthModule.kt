package ru.kvartalovea.catscafe.feature.auth.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider

val featureAuthModule = module {
    singleOf(::AuthScreenProvider) { bind<ScreenProvider>() }
}
