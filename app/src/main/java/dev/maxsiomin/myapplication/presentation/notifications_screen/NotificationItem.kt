package dev.maxsiomin.myapplication.presentation.notifications_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.maxsiomin.myapplication.domain.model.SavedNotification

@Composable
fun NotificationItem(savedNotification: SavedNotification) {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Column {
            Text(text = savedNotification.app)
            Text(text = savedNotification.title)
            Text(text = savedNotification.text)
            Text(text = savedNotification.time.toString())
        }
    }
}

@Preview
@Composable
fun NotificationItemPreview() {
    NotificationItem(
        savedNotification = SavedNotification(
            app = "org.telegram.messenger",
            text = "Hello Max",
            title = "Misha",
            time = 1691099489L,
        )
    )
}

