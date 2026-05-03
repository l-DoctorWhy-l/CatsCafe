package ru.kvartalovea.catscafe.feature.auth.impl.presentation.model

internal sealed interface AuthUiState {

    data class Login(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val emailError: String? = null,
        val passwordError: String? = null,
    ) : AuthUiState

    data class Register(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val isPasswordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val nameError: String? = null,
        val emailError: String? = null,
        val passwordError: String? = null,
        val confirmPasswordError: String? = null,
    ) : AuthUiState
}
