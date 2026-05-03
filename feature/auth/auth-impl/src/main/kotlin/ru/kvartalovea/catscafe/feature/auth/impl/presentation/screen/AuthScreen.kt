package ru.kvartalovea.catscafe.feature.auth.impl.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import ru.kvartalovea.catscafe.common.ui.asString
import ru.kvartalovea.catscafe.common.utils.UiText
import ru.kvartalovea.catscafe.feature.auth.impl.R
import ru.kvartalovea.catscafe.feature.auth.impl.presentation.model.AuthUiEvent
import ru.kvartalovea.catscafe.feature.auth.impl.presentation.model.AuthUiState
import ru.kvartalovea.catscafe.feature.auth.impl.presentation.viewmodel.AuthViewModel

@Composable
internal fun AuthScreen(
    onAuthSuccess: () -> Unit,
    viewModel: AuthViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.authSuccess.collect { onAuthSuccess() }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (val s = state) {
            is AuthUiState.Login -> LoginContent(
                state = s,
                onEvent = viewModel::onEvent,
            )
            is AuthUiState.Register -> RegisterContent(
                state = s,
                onEvent = viewModel::onEvent,
            )
        }
    }
}

// ──────────────────────────── Login ────────────────────────────

@Composable
private fun LoginContent(
    state: AuthUiState.Login,
    onEvent: (AuthUiEvent) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        AppBranding()

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.auth_sign_in_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(24.dp))

        AuthTextField(
            value = state.email,
            onValueChange = { onEvent(AuthUiEvent.LoginEmailChanged(it)) },
            label = stringResource(R.string.auth_email_label),
            error = state.emailError?.asString(),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
        )
        Spacer(modifier = Modifier.height(12.dp))

        PasswordTextField(
            value = state.password,
            onValueChange = { onEvent(AuthUiEvent.LoginPasswordChanged(it)) },
            label = stringResource(R.string.auth_password_label),
            error = state.passwordError?.asString(),
            isVisible = state.isPasswordVisible,
            onToggleVisibility = { onEvent(AuthUiEvent.LoginPasswordVisibilityToggled) },
            imeAction = ImeAction.Done,
            onImeAction = {
                focusManager.clearFocus()
                onEvent(AuthUiEvent.LoginSubmit)
            },
        )
        Spacer(modifier = Modifier.height(28.dp))

        AuthButton(
            text = stringResource(R.string.auth_sign_in_button),
            isLoading = state.isLoading,
            onClick = { onEvent(AuthUiEvent.LoginSubmit) },
        )
        Spacer(modifier = Modifier.height(16.dp))

        SwitchAuthRow(
            prompt = stringResource(R.string.auth_no_account),
            actionLabel = stringResource(R.string.auth_register_button),
            onClick = { onEvent(AuthUiEvent.OnGoToRegister) },
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// ──────────────────────────── Register ────────────────────────────

@Composable
private fun RegisterContent(
    state: AuthUiState.Register,
    onEvent: (AuthUiEvent) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        AppBranding()

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.auth_create_account_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(24.dp))

        AuthTextField(
            value = state.name,
            onValueChange = { onEvent(AuthUiEvent.RegisterNameChanged(it)) },
            label = stringResource(R.string.auth_name_label),
            error = state.nameError?.asString(),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
        )
        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(
            value = state.email,
            onValueChange = { onEvent(AuthUiEvent.RegisterEmailChanged(it)) },
            label = stringResource(R.string.auth_email_label),
            error = state.emailError?.asString(),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
        )
        Spacer(modifier = Modifier.height(12.dp))

        PasswordTextField(
            value = state.password,
            onValueChange = { onEvent(AuthUiEvent.RegisterPasswordChanged(it)) },
            label = stringResource(R.string.auth_password_label),
            error = state.passwordError?.asString(),
            isVisible = state.isPasswordVisible,
            onToggleVisibility = { onEvent(AuthUiEvent.RegisterPasswordVisibilityToggled) },
            imeAction = ImeAction.Next,
            onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
        )
        Spacer(modifier = Modifier.height(12.dp))

        PasswordTextField(
            value = state.confirmPassword,
            onValueChange = { onEvent(AuthUiEvent.RegisterConfirmPasswordChanged(it)) },
            label = stringResource(R.string.auth_repeat_password_label),
            error = state.confirmPasswordError?.asString(),
            isVisible = state.isPasswordVisible,
            onToggleVisibility = { onEvent(AuthUiEvent.RegisterPasswordVisibilityToggled) },
            imeAction = ImeAction.Done,
            onImeAction = {
                focusManager.clearFocus()
                onEvent(AuthUiEvent.RegisterSubmit)
            },
        )
        Spacer(modifier = Modifier.height(28.dp))

        AuthButton(
            text = stringResource(R.string.auth_register_button),
            isLoading = state.isLoading,
            onClick = { onEvent(AuthUiEvent.RegisterSubmit) },
        )
        Spacer(modifier = Modifier.height(16.dp))

        SwitchAuthRow(
            prompt = stringResource(R.string.auth_have_account),
            actionLabel = stringResource(R.string.auth_sign_in_button),
            onClick = { onEvent(AuthUiEvent.OnGoToLogin) },
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// ──────────────────────────── Shared components ────────────────────────────

@Composable
private fun AppBranding() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(text = "🐱", fontSize = 48.sp)
        Text(
            text = stringResource(R.string.auth_app_name),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String?,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            isError = error != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            keyboardActions = KeyboardActions(onAny = { onImeAction() }),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
            ),
        )
        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp),
            )
        }
    }
}

@Composable
private fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String?,
    isVisible: Boolean,
    onToggleVisibility: () -> Unit,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            isError = error != null,
            singleLine = true,
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onToggleVisibility) {
                    Icon(
                        imageVector = if (isVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                        contentDescription = if (isVisible) {
                            stringResource(R.string.auth_hide_password)
                        } else {
                            stringResource(R.string.auth_show_password)
                        },
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = imeAction,
            ),
            keyboardActions = KeyboardActions(onAny = { onImeAction() }),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
            ),
        )
        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 4.dp, top = 2.dp),
            )
        }
    }
}

@Composable
private fun AuthButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 2.dp,
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
private fun SwitchAuthRow(
    prompt: String,
    actionLabel: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "$prompt ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        TextButton(
            onClick = onClick,
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 4.dp),
        ) {
            Text(
                text = actionLabel,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
