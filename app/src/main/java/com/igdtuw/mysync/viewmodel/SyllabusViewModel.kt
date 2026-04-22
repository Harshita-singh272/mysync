package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SyllabusViewModel : ViewModel() {

    // List of subjects (later you can fetch from Firebase)
    val subjects = listOf(
        "Mobile Application Development",
        "Probability and Statistics",
        "Environment science",
        "Soft skills and personality development",
        "Introduction to data science",
        "Data Structure"
    )
    // Selected subject state
    var selectedSubject = mutableStateOf("Please choose your subject")
        private set

    // Dropdown expanded state
    var isExpanded = mutableStateOf(false)
        private set

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
}