package ru.kvartalovea.catscafe.feature.auth.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kvartalovea.catscafe.feature.auth.impl.domain.usecase.LoginUseCase
import ru.kvartalovea.catscafe.feature.auth.impl.domain.usecase.RegisterUseCase
import ru.kvartalovea.catscafe.feature.auth.impl.presentation.model.AuthUiEvent
import ru.kvartalovea.catscafe.feature.auth.impl.presentation.model.AuthUiState

internal class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Login())
    val state: StateFlow<AuthUiState> = _state.asStateFlow()

    private val _authSuccess = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val authSuccess: SharedFlow<Unit> = _authSuccess.asSharedFlow()

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            AuthUiEvent.OnGoToRegister -> _state.value = AuthUiState.Register()
            AuthUiEvent.OnGoToLogin -> _state.value = AuthUiState.Login()

            // ── Login ──
            is AuthUiEvent.LoginEmailChanged -> updateLogin { copy(email = event.value, emailError = null) }
            is AuthUiEvent.LoginPasswordChanged -> updateLogin { copy(password = event.value, passwordError = null) }
            AuthUiEvent.LoginPasswordVisibilityToggled -> updateLogin { copy(isPasswordVisible = !isPasswordVisible) }
            AuthUiEvent.LoginSubmit -> submitLogin()

            // ── Register ──
            is AuthUiEvent.RegisterNameChanged -> updateRegister { copy(name = event.value, nameError = null) }
            is AuthUiEvent.RegisterEmailChanged -> updateRegister { copy(email = event.value, emailError = null) }
            is AuthUiEvent.RegisterPasswordChanged -> updateRegister { copy(password = event.value, passwordError = null) }
            is AuthUiEvent.RegisterConfirmPasswordChanged -> updateRegister { copy(confirmPassword = event.value, confirmPasswordError = null) }
            AuthUiEvent.RegisterPasswordVisibilityToggled -> updateRegister { copy(isPasswordVisible = !isPasswordVisible) }
            AuthUiEvent.RegisterSubmit -> submitRegister()
        }
    }

    private fun submitLogin() {
        val s = _state.value as? AuthUiState.Login ?: return
        val emailError = validateEmail(s.email)
        val passwordError = if (s.password.isBlank()) "Введите пароль" else null
        if (emailError != null || passwordError != null) {
            _state.value = s.copy(emailError = emailError, passwordError = passwordError)
            return
        }
        updateLogin { copy(isLoading = true) }
        viewModelScope.launch {
            loginUseCase(s.email.trim(), s.password)
                .onSuccess { _authSuccess.tryEmit(Unit) }
                .onFailure { updateLogin { copy(isLoading = false) } }
        }
    }

    private fun submitRegister() {
        val s = _state.value as? AuthUiState.Register ?: return
        val nameError = if (s.name.isBlank()) "Введите имя" else null
        val emailError = validateEmail(s.email)
        val passwordError = validatePassword(s.password)
        val confirmError = if (s.confirmPassword != s.password) "Пароли не совпадают" else null
        if (nameError != null || emailError != null || passwordError != null || confirmError != null) {
            _state.value = s.copy(
                nameError = nameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmError,
            )
            return
        }
        updateRegister { copy(isLoading = true) }
        viewModelScope.launch {
            registerUseCase(s.name.trim(), s.email.trim(), s.password)
                .onSuccess { _authSuccess.tryEmit(Unit) }
                .onFailure { updateRegister { copy(isLoading = false) } }
        }
    }

    private inline fun updateLogin(transform: AuthUiState.Login.() -> AuthUiState.Login) {
        (_state.value as? AuthUiState.Login)?.let { _state.value = it.transform() }
    }

    private inline fun updateRegister(transform: AuthUiState.Register.() -> AuthUiState.Register) {
        (_state.value as? AuthUiState.Register)?.let { _state.value = it.transform() }
    }

    private fun validateEmail(email: String): String? = when {
        email.isBlank() -> "Введите email"
        !email.contains('@') -> "Неверный формат email"
        else -> null
    }

    private fun validatePassword(password: String): String? = when {
        password.isBlank() -> "Введите пароль"
        password.length < 6 -> "Минимум 6 символов"
        else -> null
    }
}
