package dev.maxsiomin.myapplication.notifications.domain.model

data class SavedNotification(

    val app: String,

    // Epoch seconds
    val time: Long,

    val title: String,

    val text: String,

)
