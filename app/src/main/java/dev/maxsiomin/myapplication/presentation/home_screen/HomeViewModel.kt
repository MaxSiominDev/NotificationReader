package dev.maxsiomin.myapplication.presentation.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(HomeState())

    sealed class Event {

    }

    fun onEvent(event: Event) {

    }

}
