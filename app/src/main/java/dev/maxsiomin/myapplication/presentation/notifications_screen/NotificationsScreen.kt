package dev.maxsiomin.myapplication.presentation.notifications_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.maxsiomin.myapplication.domain.model.SavedNotification

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(state: NotificationsScreenState, onEvent: (NotificationsViewModel.Event) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "All notifications")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onEvent(NotificationsViewModel.Event.Refresh)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) { values ->
        LazyColumn(Modifier.fillMaxSize().padding(values)) {
            items(state.notificationsList.size) { index ->
                NotificationItem(savedNotification = state.notificationsList.elementAt(index))
            }
        }
    }
}

@Preview
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreen(
        state = NotificationsScreenState(
            notificationsList = listOf(
                SavedNotification(
                    app = "org.telegram.messenger",
                    title = "Misha",
                    text = "Hello Max",
                    time = 1691099489L,
                ),
                SavedNotification(
                    app = "org.telegram.messenger",
                    title = "Misha",
                    text = "Hello Max",
                    time = 1691099489L,
                ),
            )
        ),
        {}
    )
}
