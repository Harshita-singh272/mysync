package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.igdtuw.mysync.model.AttendanceRecord

data class SubjectAttendance(
    val name: String = "",
    val attended: Int = 0,
    val total: Int = 0,
    val percentage: Float = 0f
)

class AttendanceViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    var studentList = mutableStateListOf<AttendanceRecord>()
    var subjects = mutableStateListOf<String>()
    var subjectAttendance = mutableStateListOf<SubjectAttendance>()

    init {
        fetchStudentsFromWhitelist()
        fetchSubjects()
    }

    private fun fetchStudentsFromWhitelist() {
        db.collection("users").get().addOnSuccessListener { result ->
            studentList.clear()
            val list = result.map { doc ->
                AttendanceRecord(
                    studentName = doc.getString("name") ?: "Unknown",
                    enrollmentNo = doc.getString("enrollmentNo") ?: "",
                    email = doc.id,
                    isPresent = false
                )
            }
            studentList.addAll(list.sortedBy { it.studentName })
        }
    }

    private fun fetchSubjects() {
        db.collection("subjects").addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                subjects.clear()
                subjects.addAll(snapshot.map { it.getString("name") ?: "" })
            }
        }
    }

    fun fetchStudentAttendance(studentEmail: String) {
        if (studentEmail.isBlank()) return

        // Step 1: Ensure subjects are fetched first
        db.collection("subjects").get().addOnSuccessListener { subjectDocs ->
            val allSubjects = subjectDocs.map { it.getString("name") ?: "" }
            subjectAttendance.clear()

            // Step 2: For each subject, get the attendance record
            allSubjects.forEach { subjectName ->
                db.collection("attendance").document(subjectName).addSnapshotListener { doc, _ ->
                    val data = doc?.data ?: emptyMap<String, Any>()
                    var attendedCount = 0
                    val totalClasses = data.size

                    data.forEach { (_, value) ->
                        val presentList = value as? List<*>
                        if (presentList?.any { it.toString().trim().lowercase() == studentEmail.trim().lowercase() } == true) {
                            attendedCount++
                        }
                    }

                    val percent = if (totalClasses > 0) (attendedCount.toFloat() / totalClasses) * 100 else 0f
                    val record = SubjectAttendance(subjectName, attendedCount, totalClasses, percent)

                    val index = subjectAttendance.indexOfFirst { it.name == subjectName }
                    if (index != -1) {
                        subjectAttendance[index] = record
                    } else {
                        subjectAttendance.add(record)
                    }
                }
            }

            // Step 3: If no subjects exist at all, add a "No Data" placeholder to stop the spinner
            if (allSubjects.isEmpty()) {
                subjectAttendance.add(SubjectAttendance("No subjects found", 0, 0, 0f))
            }
        }
    }

    fun markAllPresent(status: Boolean) {
        for (i in studentList.indices) {
            studentList[i] = studentList[i].copy(isPresent = status)
        }
    }

    fun uploadAttendance(subject: String, date: String) {
        val presentEmails = studentList.filter { it.isPresent }.map { it.email.trim().lowercase() }
        db.collection("attendance").document(subject)
            .update(date, presentEmails)
            .addOnFailureListener {
                db.collection("attendance").document(subject).set(mapOf(date to presentEmails))
            }
    }

    fun addSubject(name: String) {
        db.collection("subjects").add(mapOf("name" to name))
    }
}