package com.igdtuw.mysync.viewmodel


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.Student
import com.igdtuw.mysync.model.SubjectAttendance

class AttendanceViewModel : ViewModel() {
    // CR State
    var studentList by mutableStateOf(listOf(
        Student(1, "Aarav Sharma"),
        Student(2, "Ishani Gupta"),
        Student(3, "Rohan Mehra"),
        Student(4, "Sanya Malhotra"),
        Student(5, "Vikram Singh")
    ))

    // Student State
    var subjectAttendance by mutableStateOf(listOf(
        SubjectAttendance("Mathematics", 18, 20),
        SubjectAttendance("Data Structures", 12, 20),
        SubjectAttendance("Operating Systems", 22, 25),
        SubjectAttendance("Computer Networks", 14, 20)
    ))

    fun updateAttendance(id: Int, status: Boolean) {
        studentList = studentList.map {
            if (it.id == id) it.copy(isPresent = status) else it
        }
    }
}