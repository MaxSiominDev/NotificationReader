package dev.maxsiomin.myapplication.presentation.home_screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.maxsiomin.myapplication.presentation.Screen

@Composable
fun HomeScreen(navController: NavController, state: HomeState, onEvent: (HomeViewModel.Event) -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                // TODO how to start activity from composable
                context.startActivity(intent)
            }) {
            Text(text = "Notifications Settings")
        }
        Button(
            onClick = {
                navController.navigate(Screen.NotificationsScreen.route)
            }) {
            Text(text = "View notifications")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        state = HomeState(),
        onEvent = {}
    )
}
