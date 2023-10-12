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
import java.util.Calendar

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
            val calendar = Calendar.getInstance().apply {
                clear()
                timeInMillis = savedNotification.time
            }
            val time = calendar.asDateTime()
            Text(text = time)
        }
    }
}

private fun Calendar.asDateTime(): String {
    val month = this[Calendar.MONTH] + 1
    val day = this[Calendar.DAY_OF_MONTH]
    val year = this[Calendar.YEAR].toString().takeLast(2)
    val hour = this[Calendar.HOUR]
    val minute = this[Calendar.MINUTE]
    val amPm = if (this[Calendar.AM_PM] == 1) "PM" else "AM"
    return "$month/$day/$year $hour:$minute $amPm"
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

