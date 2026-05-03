package ru.kvartalovea.catscafe.feature.profile.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kvartalovea.catscafe.core.account.api.AccountManager
import ru.kvartalovea.catscafe.core.navigation.api.AppNavigator
import ru.kvartalovea.catscafe.feature.auth.api.AuthRoute
import ru.kvartalovea.catscafe.feature.donations.api.DonationsRoute
import ru.kvartalovea.catscafe.feature.mybookings.api.MyBookingsRoute
import ru.kvartalovea.catscafe.feature.profile.impl.presentation.model.ProfileUiEvent
import ru.kvartalovea.catscafe.feature.profile.impl.presentation.model.ProfileUiState

internal class ProfileViewModel(
    private val accountManager: AccountManager,
    private val navigator: AppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileUiState>(buildState())
    val state: StateFlow<ProfileUiState> = _state.asStateFlow()

    fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.OnMyBookingsClick -> navigator.navigate(MyBookingsRoute)
            ProfileUiEvent.OnDonationsHistoryClick -> navigator.navigate(DonationsRoute)
            ProfileUiEvent.OnNotificationSettingsClick -> Unit
            ProfileUiEvent.OnLogoutClick -> logout()
        }
    }

    private fun logout() {
        accountManager.logout()
        navigator.navigateAndClearStack(AuthRoute)
    }

    private fun buildState(): ProfileUiState {
        val user = accountManager.getCurrentUser() ?: return ProfileUiState.NoAuth
        return ProfileUiState.Content(
            userName = user.name,
            userEmail = user.email,
            loyaltyPoints = user.loyaltyPoints,
        )
    }
}
