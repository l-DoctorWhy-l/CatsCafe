package ru.kvartalovea.catscafe.feature.splash.api

import kotlinx.serialization.Serializable

/**
 * Стартовый экран приложения. На нём решается, куда вести пользователя:
 *
 * После выхода со Splash он удаляется из back stack (`popUpTo<SplashRoute> { inclusive = true }`).
 */
@Serializable
data object SplashRoute
