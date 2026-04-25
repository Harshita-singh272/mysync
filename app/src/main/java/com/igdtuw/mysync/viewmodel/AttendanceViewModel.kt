package com.igdtuw.mysync.viewmodel


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.Student
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class AttendanceViewModel : ViewModel() {

    var studentList by mutableStateOf(
        (1..10).map {
            Student(
                id = it,
                name = "Student $it",
                rollNumber = (100 + it).toString()
            )
        }
    )
    var subjects = listOf(
        "DSA",
        "Mobile Application Development",
        "Introduction to Data Science",
        "EVS",
        "Probability and Statistics",
        "SSPD"
    )
    var selectedSubject by mutableStateOf(subjects[0])
    var selectedDate by mutableStateOf("23/04/2026")

    var attendanceData by mutableStateOf(
        mutableMapOf<String, MutableMap<String, MutableMap<Int, Boolean>>>()
    )

    fun loadAttendance() {
        val subjectData = attendanceData[selectedSubject]
        val dateData = subjectData?.get(selectedDate)

        studentList = studentList.map {
            it.copy(isPresent = dateData?.get(it.id))
        }
    }

    fun updateAttendance(id: Int, status: Boolean) {

        studentList = studentList.map {
            if (it.id == id) it.copy(isPresent = status) else it
        }
        
        val subjectMap = attendanceData.getOrPut(selectedSubject) { mutableMapOf() }
        val dateMap = subjectMap.getOrPut(selectedDate) { mutableMapOf() }

        dateMap[id] = status
    }
}