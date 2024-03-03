package dev.maxsiomin.myapplication.home.presentation.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.myapplication.core.presentation.Screen
import dev.maxsiomin.myapplication.core.util.NotificationChecker
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val notificationChecker: NotificationChecker): ViewModel() {

    var state by mutableStateOf(
        HomeState(
            notificationChecker.hasNotificationReadingPermission()
        )
    )
        private set

    sealed class Event {
        data object CheckIfHasNotificationPermission : Event()
        data class OnViewNotificationClicked(val navController: NavController) : Event()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.CheckIfHasNotificationPermission -> updateHomeStateIfNotificationPermissionChanged()
            is Event.OnViewNotificationClicked -> event.navController.navigate(Screen.NotificationsScreen.route)
        }
    }

    private fun updateHomeStateIfNotificationPermissionChanged() {
        state = state.copy(
            hasNotificationPermission = notificationChecker.hasNotificationReadingPermission()
        )
    }

}
