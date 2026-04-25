package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.igdtuw.mysync.model.AttendanceRecord
import com.igdtuw.mysync.model.SubjectModel
import com.igdtuw.mysync.model.SubjectAttendance

class AttendanceViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    var subjectAttendance = mutableStateListOf<SubjectAttendance>()
    var studentList = mutableStateListOf<AttendanceRecord>()
    var subjects = mutableStateListOf<SubjectModel>()

    init {
        fetchStudents()
        listenToSubjects()
    }

    fun fetchStudents() {
        db.collection("users").whereEqualTo("role", "Student").get()
            .addOnSuccessListener { result ->
                studentList.clear()
                for (doc in result) {
                    studentList.add(AttendanceRecord(doc.id, doc.getString("name") ?: "Unknown", false))
                }
            }
    }

    private fun listenToSubjects() {
        db.collection("subjects").addSnapshotListener { v, _ ->
            v?.let {
                subjects.clear()
                for (doc in it) {
                    val sub = doc.toObject(SubjectModel::class.java)
                    subjects.add(sub.copy(id = doc.id))
                }
            }
        }
    }

    fun addSubject(name: String) {
        if (name.isBlank()) return

        val id = db.collection("subjects").document().id
        val subjectData = SubjectModel(id = id, name = name, totalClasses = 0)

        db.collection("subjects").document(id)
            .set(subjectData)
            .addOnSuccessListener {
                println("Debug: Subject $name added successfully!")
            }
            .addOnFailureListener { e ->
                println("Debug: Failed to add subject: ${e.message}")
            }
    }
    fun markAllPresent(status: Boolean) {
        for (i in studentList.indices) studentList[i] = studentList[i].copy(isPresent = status)
    }

    fun uploadAttendance(subject: String, date: String) {
        val data = hashMapOf(
            "subject" to subject,
            "date" to date,
            "students" to studentList.map { mapOf("name" to it.studentName, "status" to if (it.isPresent) "P" else "A") }
        )
        db.collection("attendance").document("${subject}_$date").set(data)
    }

    fun fetchStudentAttendance(studentName: String) {
        db.collection("attendance").addSnapshotListener { v, _ ->
            val summary = mutableMapOf<String, Pair<Int, Int>>()
            v?.forEach { doc ->
                val sub = doc.getString("subject") ?: "Unknown"
                val list = doc.get("students") as? List<Map<String, String>> ?: listOf()
                val isP = list.any { it["name"].equals(studentName, ignoreCase = true) && it["status"] == "P" }
                val cur = summary.getOrDefault(sub, Pair(0, 0))
                summary[sub] = Pair(cur.first + (if (isP) 1 else 0), cur.second + 1)
            }
            subjectAttendance.clear()
            subjectAttendance.addAll(summary.map { SubjectAttendance(it.key, it.value.first, it.value.second) })
        }
    }
}