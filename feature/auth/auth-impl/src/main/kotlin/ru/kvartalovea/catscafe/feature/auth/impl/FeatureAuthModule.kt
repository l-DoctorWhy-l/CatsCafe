package ru.kvartalovea.catscafe.feature.auth.impl

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.kvartalovea.catscafe.core.navigation.api.ScreenProvider
import ru.kvartalovea.catscafe.feature.auth.impl.data.remote.api.AuthApiService
import ru.kvartalovea.catscafe.feature.auth.impl.data.repository.AuthRepositoryImpl
import ru.kvartalovea.catscafe.feature.auth.impl.domain.repository.AuthRepository
import ru.kvartalovea.catscafe.feature.auth.impl.domain.usecase.LoginUseCase
import ru.kvartalovea.catscafe.feature.auth.impl.domain.usecase.RegisterUseCase
import ru.kvartalovea.catscafe.feature.auth.impl.presentation.viewmodel.AuthViewModel

val featureAuthModule = module {
    singleOf(::AuthScreenProvider) { bind<ScreenProvider>() }

    single { get<Retrofit>().create(AuthApiService::class.java) }

    single<AuthRepository> { AuthRepositoryImpl(api = get(), accountManager = get()) }
    single { LoginUseCase(get()) }
    single { RegisterUseCase(get()) }
    viewModelOf(::AuthViewModel)
}
