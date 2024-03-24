package dev.maxsiomin.myapplication.core.presentation

sealed class Screen(val route: String) {

    data object HomeScreen : Screen("home_screen")
    data object NotificationsScreen : Screen("notifications_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}