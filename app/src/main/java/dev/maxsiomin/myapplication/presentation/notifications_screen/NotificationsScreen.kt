package dev.maxsiomin.myapplication.presentation.notifications_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.maxsiomin.myapplication.domain.model.SavedNotification

@Composable
fun NotificationsScreen(state: NotificationsScreenState) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(state.notificationsList.size) { index ->
            NotificationItem(savedNotification = state.notificationsList.elementAt(index))
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
        )
    )
}
