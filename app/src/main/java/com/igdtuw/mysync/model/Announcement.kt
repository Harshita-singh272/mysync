package com.igdtuw.mysync.model

import com.google.firebase.Timestamp

data class Announcement(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "General",
    val timestamp: Timestamp = Timestamp.now()
)