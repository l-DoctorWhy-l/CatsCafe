package ru.kvartalovea.catscafe.feature.auth.impl.presentation.model

import ru.kvartalovea.catscafe.common.utils.UiText

internal sealed interface AuthUiState {

    data class Login(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val emailError: UiText? = null,
        val passwordError: UiText? = null,
    ) : AuthUiState

    data class Register(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val nameError: UiText? = null,
        val emailError: UiText? = null,
        val passwordError: UiText? = null,
        val confirmPasswordError: UiText? = null,
    ) : AuthUiState
}
