package ru.kvartalovea.catscafe.feature.auth.api

import kotlinx.serialization.Serializable

/**
 * Экран авторизации. Открывается:
 *  - со Splash, если пользователь не авторизован;
 *  - из Profile при logout (с `popUpTo` корня).
 *
 * После успешного входа экран навигирует на `HomeRoute` и удаляется
 * из back stack: `popUpTo<AuthRoute> { inclusive = true }`.
 */
@Serializable
data object AuthRoute
