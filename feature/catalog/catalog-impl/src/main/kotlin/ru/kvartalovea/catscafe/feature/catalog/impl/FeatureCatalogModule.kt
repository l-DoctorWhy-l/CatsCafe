package ru.kvartalovea.catscafe.feature.catalog.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider

val featureCatalogModule = module {
    singleOf(::CatalogScreenProvider) { bind<ScreenProvider>() }
}
