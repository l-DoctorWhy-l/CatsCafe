package ru.kvartalovea.catscafe.feature.profile.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.profile.impl.presentation.viewmodel.ProfileViewModel

val featureProfileModule = module {
    singleOf(::ProfileScreenProvider) { bind<ScreenProvider>() }
    viewModelOf(::ProfileViewModel)
}
