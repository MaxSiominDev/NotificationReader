package dev.maxsiomin.myapplication.domain.model

data class SavedNotification(

    val app: String,

    // Epoch seconds
    val time: Long,

    val title: String,

    val text: String,

)
