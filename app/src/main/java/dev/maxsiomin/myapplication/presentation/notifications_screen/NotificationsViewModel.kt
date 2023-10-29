package dev.maxsiomin.myapplication.presentation.notifications_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.myapplication.domain.model.SavedNotification
import dev.maxsiomin.myapplication.domain.repository.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repo: NotificationsRepository
) : ViewModel() {

    var state by mutableStateOf(NotificationsScreenState())
        private set

    private var notificationsList = listOf<SavedNotification>()

    init {
        loadAllNotifications()
    }

    sealed class UiEvent {
        data object ClearFocus : UiEvent()
        data class Navigate(val navigate: NavController.() -> Unit) : UiEvent()
    }

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private fun loadAllNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                notificationsList = repo.loadAllNotifications()
                search()
            }
        }
    }

    sealed class Event {
        data object Refresh : Event()
        data class GoBack(val navController: NavController) : Event()
        data object OpenSearch : Event()
        data class SearchBoxTextChanged(val newText: String) : Event()
        data object CloseSearch : Event()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.Refresh -> loadAllNotifications()
            is Event.GoBack -> goBack()
            is Event.OpenSearch -> state = state.copy(searchBoxExpanded = true)
            is Event.SearchBoxTextChanged -> onSearchBoxTextChanged(event.newText)
            is Event.CloseSearch -> state = state.copy(searchBoxExpanded = false)
        }
    }

    private fun onSearchBoxTextChanged(newText: String) {
        state = state.copy(searchBoxContent = newText)
        viewModelScope.launch {
            val currentValue = state.searchBoxContent
            delay(300)
            if (state.searchBoxContent != currentValue) return@launch
            search()
        }
    }

    private fun search() {
        if (state.searchBoxContent == "") {
            state = state.copy(notificationsList = notificationsList)
            return
        }
        val filteredList = notificationsList.filter {
            filterItem(it, state.searchBoxContent)
        }
        state = state.copy(notificationsList = filteredList)
    }

    private fun filterItem(item: SavedNotification, query: String): Boolean {
        return listOf(item.text, item.app, item.title).any {
            it.contains(query, ignoreCase = true)
        }
    }

    private fun goBack() {
        viewModelScope.launch {
            _eventFlow.emit(
                UiEvent.Navigate { popBackStack() }
            )
        }
    }

}
