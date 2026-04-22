package com.igdtuw.mysync.model

data class DashboardModel(
    val user: String = "",
    val email: String = "",
    val branch: String = "",
    val total: Int = 0,
    val thisWeekAnnouncements: Int = 0,
    val lastWeekAnnouncements: Int = 0
)