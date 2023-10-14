package dev.maxsiomin.myapplication.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.maxsiomin.myapplication.presentation.home_screen.HomeScreen
import dev.maxsiomin.myapplication.presentation.home_screen.HomeViewModel
import dev.maxsiomin.myapplication.presentation.notifications_screen.NotificationsScreen
import dev.maxsiomin.myapplication.presentation.notifications_screen.NotificationsViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route,
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController, viewModel.state, viewModel::onEvent)
        }

        composable(
            route = Screen.NotificationsScreen.route
        ) {
            //val viewModel = hiltViewModel<NotificationsViewModel>()
            NotificationsScreen(navController = navController)
        }
    }
}
