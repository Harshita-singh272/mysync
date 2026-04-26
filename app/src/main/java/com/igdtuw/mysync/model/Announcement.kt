package com.igdtuw.mysync.model

import com.google.firebase.Timestamp

data class Announcement(
    val id: String = "",
    val title: String = "",
    val description: String = "", // Make sure this is 'description'
    val category: String = "General",
    val timestamp: Timestamp = Timestamp.now()
)