package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.TimetableData

class SyllabusViewModel : ViewModel() {

    val subjects = listOf(
        "Mobile Application Development",
        "Probability and Statistics",
        "Environment science",
        "Soft skills and personality development",
        "Introduction to data science",
        "Data Structure"
    )

    private val syllabusList = listOf(
        TimetableData("Mobile Application Development", "https://drive.google.com/file/d/1z-6puK8XqJ1hKDnyALvZW2hGSZ95f4vL/view?usp=drive_link", 1),
        TimetableData("Probability and Statistics", "https://drive.google.com/file/d/1XCNIm9-Lo82aU_J-cyW0Tb628UcN7i2O/view?usp=drive_link", 1),
        TimetableData("Environment science", "https://drive.google.com/file/d/1n-LlPjn8E-UniMttiwgsmwVIBmLcjaZq/view?usp=drive_link", 1),
        TimetableData("Soft skills and personality development", "https://drive.google.com/file/d/1YLYXMszzQou7BofpMcG_kFnxrMYzoARD/view?usp=drive_link", 1),
        TimetableData("Introduction to data science", "https://drive.google.com/file/d/1aQtLl2kWHuu_rXx6KAf9iuwDTS39iiUd/view?usp=drive_link", 1),
        TimetableData("Data Structure", "https://drive.google.com/file/d/1UESjSSBdYFHSr9P8CUBBWiYmtZCf_Kks/view?usp=drive_link", 1)
    )

    var selectedSubject = mutableStateOf("Please choose your subject")
    var isExpanded = mutableStateOf(false)

    var selectedPdfUrl = mutableStateOf("")

    fun onSubjectSelected(subject: String) {
        selectedSubject.value = subject
        isExpanded.value = false
    }

    fun toggleDropdown() {
        isExpanded.value = !isExpanded.value
    }

    fun closeDropdown() {
        isExpanded.value = false
    }

    fun loadSyllabus() {
        val data = syllabusList.find {
            it.branch == selectedSubject.value
        }

        selectedPdfUrl.value = data?.pdfUrl ?: ""
    }
}