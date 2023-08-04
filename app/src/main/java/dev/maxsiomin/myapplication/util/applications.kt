package dev.maxsiomin.myapplication.util

fun getApplicationByPackageName(pkg: String): String {
    return if (pkg in applications.keys) {
        applications[pkg]!!
    } else {
        pkg
    }
}

private val applications = mapOf(
    "org.telegram.messenger" to "Telegram",
    "org.telegram.messenger.web" to "Telegram APK",
)
