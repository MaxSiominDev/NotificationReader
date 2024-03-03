package dev.maxsiomin.myapplication.notifications.presentation.notifications_screen

import dev.maxsiomin.myapplication.notifications.domain.model.SavedNotification

data class NotificationsScreenState(

    val notificationsList: List<SavedNotification> = emptyList(),

    val searchBoxExpanded: Boolean = false,

    val searchBoxContent: String = "",

    )
