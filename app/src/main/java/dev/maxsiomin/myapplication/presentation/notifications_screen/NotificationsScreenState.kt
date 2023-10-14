package dev.maxsiomin.myapplication.presentation.notifications_screen

import dev.maxsiomin.myapplication.domain.model.SavedNotification

data class NotificationsScreenState(

    val notificationsList: List<SavedNotification> = emptyList(),

    val searchBoxExpanded: Boolean = false,

    val searchBoxContent: String = "",

)
