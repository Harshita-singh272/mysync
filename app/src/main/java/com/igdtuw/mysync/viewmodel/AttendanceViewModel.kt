package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
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
                val subjectList = snapshot.map { doc -> doc.getString("name") ?: "" }
                subjects.addAll(subjectList)
            }
        }
    }

    fun fetchStudentAttendance(studentName: String) {

        subjectAttendance.clear()
        subjects.forEach { subject ->
            subjectAttendance.add(SubjectAttendance(name = subject, attended = 0, total = 0, percentage = 0f))
        }
    }

    fun addSubject(name: String) {
        db.collection("subjects").add(mapOf("name" to name))
    }

    fun markAllPresent(status: Boolean) {
        for (i in studentList.indices) {
            studentList[i] = studentList[i].copy(isPresent = status)
        }
    }

    fun uploadAttendance(subject: String, date: String) {
        val batch = db.batch()
        studentList.forEach { student ->
            val docRef = db.collection("attendance").document(date).collection(subject).document(student.email)
            batch.set(docRef, mapOf(
                "name" to student.studentName,
                "status" to if (student.isPresent) "P" else "A",
                "enrollmentNo" to student.enrollmentNo
            ))
        }
        batch.commit()
    }
}
//package com.igdtuw.mysync.viewmodel
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.DatabaseReference
//import androidx.compose.runtime.mutableStateListOf
//import androidx.lifecycle.ViewModel
//import com.igdtuw.mysync.model.AttendanceRecord
//
//class AttendanceViewModel : ViewModel() {
//    private val database = FirebaseDatabase.getInstance().getReference("Attendance")
//
//    var studentList = mutableStateListOf<AttendanceRecord>()
//        private set
//
//    var subjects = mutableStateListOf<String>()
//        private set
//
//    // Mock data loader - Replace with your Firebase fetch logic for students
//    init {
//        loadInitialData()
//    }
//
//    private fun loadInitialData() {
//        // Example initial load
//        subjects.addAll(listOf("Applied Physics", "Manufacturing Processes", "Data Structures"))
//        studentList.addAll(listOf(
//            AttendanceRecord("Aayushree Malviya", "00101012024", "aayushree@igdtuw.ac.in", false),
//            AttendanceRecord("Adya", "00201012024", "adya@igdtuw.ac.in", false)
//        ))
//    }
//
//    fun toggleStudentStatus(email: String, isChecked: Boolean) {
//        val index = studentList.indexOfFirst { it.email == email }
//        if (index != -1) {
//            // Replacing the object in the list triggers the UI update
//            studentList[index] = studentList[index].copy(isPresent = isChecked)
//        }
//    }
//
//    fun markAllPresent(isPresent: Boolean) {
//        for (i in studentList.indices) {
//            studentList[i] = studentList[i].copy(isPresent = isPresent)
//        }
//    }
//
//    fun addSubject(name: String) {
//        if (!subjects.contains(name)) subjects.add(name)
//    }
//
//    fun uploadAttendance(subject: String, date: String) {
//        val attendanceData = studentList.map {
//            mapOf(
//                "name" to it.studentName,
//                "enrollment" to it.enrollmentNo,
//                "status" to if (it.isPresent) "Present" else "Absent"
//            )
//        }
//
//        // Stores in: Attendance -> SubjectName -> Date -> StudentList
//        database.child(subject).child(date).setValue(attendanceData)
//    }
//}