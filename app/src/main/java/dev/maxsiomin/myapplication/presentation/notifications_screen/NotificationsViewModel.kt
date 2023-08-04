package dev.maxsiomin.myapplication.presentation.notifications_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.myapplication.domain.model.SavedNotification
import dev.maxsiomin.myapplication.domain.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repo: NotificationsRepository
) : ViewModel() {

    var state by mutableStateOf(
        NotificationsScreenState(
            emptyList()
        )
    )

    init {
        loadAllNotifications()
    }

    private fun loadAllNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                state = state.copy(notificationsList = repo.loadAllNotifications())
            }
        }
    }

}
