package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.igdtuw.mysync.model.Announcement
import com.google.firebase.Timestamp

class AnnouncementViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    var announcements = mutableStateListOf<Announcement>()
        private set

    init {
        listenForAnnouncements()
    }

    private fun listenForAnnouncements() {
        db.collection("announcements")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) return@addSnapshotListener

                value?.let {
                    announcements.clear()
                    for (doc in it) {
                        // Ensure the mapping matches your Model exactly
                        val announcement = doc.toObject(Announcement::class.java).copy(id = doc.id)
                        announcements.add(announcement)
                    }
                }
            }
    }

    fun updateAnnouncement(id: String, newTitle: String, newDesc: String) {
        if (id.isNotEmpty()) {
            val updates = mapOf(
                "title" to newTitle,
                "description" to newDesc
            )
            db.collection("announcements").document(id).update(updates)
                .addOnSuccessListener { println("Update successful") }
                .addOnFailureListener { e -> println("Error updating: ${e.message}") }
        }
    }

    fun addAnnouncement(title: String, description: String, category: String) {
        if (title.isNotBlank() && description.isNotBlank()) {
            val newDoc = db.collection("announcements").document()
            val announcement = Announcement(
                id = newDoc.id,
                title = title,
                description = description,
                category = category,
                timestamp = Timestamp.now() // Explicitly set current time
            )
            newDoc.set(announcement)
        }
    }

    fun deleteAnnouncement(id: String) {
        if (id.isNotEmpty()) {
            db.collection("announcements").document(id).delete()
        }
    }
}