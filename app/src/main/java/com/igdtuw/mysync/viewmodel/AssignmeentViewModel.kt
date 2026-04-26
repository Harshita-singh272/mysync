package com.igdtuw.mysync.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.igdtuw.mysync.model.AssignmentItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AssignmentViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("assignments")
    val selectedAssignment = MutableStateFlow<AssignmentItem?>(null)
    val subjects = listOf(
        "Soft Skills & Personality Development",
        "Probability & Statistics",
        "Mobile Application Development",
        "Data Structures",
        "Introduction to Data Science",
        "Environmental Science"
    )

    private val _assignments = MutableStateFlow<List<AssignmentItem>>(emptyList())
    val assignments: StateFlow<List<AssignmentItem>> = _assignments

    init {
        collection.addSnapshotListener { snapshot, _ ->
            val list = snapshot?.documents?.mapNotNull {
                it.toObject(AssignmentItem::class.java)?.apply {
                    id = it.id
                }
            } ?: emptyList()

            _assignments.value = list
        }
    }

    fun addAssignment(item: AssignmentItem) {
        collection.add(item)
    }
    fun deleteAssignment(id: String){
        collection.document(id).delete()
    }
    fun updateAssignment(item: AssignmentItem) {
        collection.document(item.id).set(item)
    }
}