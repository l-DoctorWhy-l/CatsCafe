package ru.kvartalovea.catscafe.feature.auth.impl.presentation.model

internal sealed interface AuthUiEvent {
    data object OnGoToRegister : AuthUiEvent
    data object OnGoToLogin : AuthUiEvent

    // Login form
    data class LoginEmailChanged(val value: String) : AuthUiEvent
    data class LoginPasswordChanged(val value: String) : AuthUiEvent
    data object LoginPasswordVisibilityToggled : AuthUiEvent
    data object LoginSubmit : AuthUiEvent

    // Register form
    data class RegisterNameChanged(val value: String) : AuthUiEvent
    data class RegisterEmailChanged(val value: String) : AuthUiEvent
    data class RegisterPasswordChanged(val value: String) : AuthUiEvent
    data class RegisterConfirmPasswordChanged(val value: String) : AuthUiEvent
    data object RegisterPasswordVisibilityToggled : AuthUiEvent
    data object RegisterSubmit : AuthUiEvent
}
