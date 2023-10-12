package dev.maxsiomin.myapplication.presentation.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.myapplication.util.NotificationChecker
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val notificationChecker: NotificationChecker): ViewModel() {

    var state by mutableStateOf(
        HomeState(
            notificationChecker.hasNotificationReadingPermission()
        )
    )

    sealed class Event {
        data object CheckIfHasNotificationPermission : Event()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.CheckIfHasNotificationPermission -> updateHomeStateIfNotificationPermissionChanged()
        }
    }

    private fun updateHomeStateIfNotificationPermissionChanged() {
        state = state.copy(
            hasNotificationPermission = notificationChecker.hasNotificationReadingPermission()
        )
    }

}
