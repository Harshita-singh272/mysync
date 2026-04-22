package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.Announcement

class AnnouncementViewModel : ViewModel() {

    private val _announcements = mutableStateListOf<Announcement>()

    val announcements: List<Announcement>
        get() = _announcements

    fun addAnnouncement(title: String, description: String) {
        if (title.isNotBlank() && description.isNotBlank()) {
            _announcements.add(Announcement(title, description))
        }
    }

    fun deleteAnnouncement(announcement: Announcement) {
        _announcements.remove(announcement)
    }

    fun updateAnnouncement(
        old: Announcement,
        newTitle: String,
        newDesc: String
    ) {
        val index = _announcements.indexOf(old)
        if (index != -1) {
            _announcements[index] = Announcement(newTitle, newDesc)
        }
    }
}