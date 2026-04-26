package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.TimetableData

class TimetableViewModel : ViewModel() {

    val branches = listOf(
        "Cse-1", "Cse-2", "Cse-3",
        "CseAi-1", "CseAi-2", "CseAi-3"
    )

//    private val pdfUrlcse1 =
//        "https://drive.google.com/uc?export=download&id=1L6q1x0ofQdHqZLgCiQspe-aaWCdvfmFE"
//
//    private val pdfUrlcse2 =
//        "https://drive.google.com/uc?export=download&id=1mW5OazbrSSIaA9GoBSUVBiRLET-kHxul"
//
//    private val pdfUrlcse3 =
//        "https://drive.google.com/uc?export=download&id=1c-3czR3gNP_5QS9ngrGEp1Y_4RKbdPGR"
//
//    private val pdfUrlcseAi1 =
//        "https://drive.google.com/uc?export=download&id=1klVUUwIJ8Hn-XahF7Y3sIhfAGQItzF91"
//
//    private val pdfUrlcseAi2 =
//        "https://drive.google.com/uc?export=download&id=1Us97DWoKFczSdbNGrvB6NVjvhn2L4szu"
//
//    private val pdfUrlcseAi3 =
//        "https://drive.google.com/uc?export=download&id=1jV9LcI7MJeeiAHzNLsM662EjP1mUQw8k"
    private val timetableList = listOf(
        TimetableData("Cse-1", "https://drive.google.com/file/d/1L6q1x0ofQdHqZLgCiQspe-aaWCdvfmFE/view?usp=drive_link", 1),
        TimetableData("Cse-2", "https://drive.google.com/file/d/1mW5OazbrSSIaA9GoBSUVBiRLET-kHxul/view?usp=drive_link", 1),
        TimetableData("Cse-3", "https://drive.google.com/file/d/1c-3czR3gNP_5QS9ngrGEp1Y_4RKbdPGR/view?usp=drive_link", 1),
        TimetableData("CseAi-1", "https://drive.google.com/file/d/1klVUUwIJ8Hn-XahF7Y3sIhfAGQItzF91/view?usp=drive_link", 1),
        TimetableData("CseAi-2","https://drive.google.com/file/d/1Us97DWoKFczSdbNGrvB6NVjvhn2L4szu/view?usp=drive_link", 1),
        TimetableData("CseAi-3", "https://drive.google.com/file/d/1jV9LcI7MJeeiAHzNLsM662EjP1mUQw8k/view?usp=drive_link", 1)
    )

    var selectedBranch = mutableStateOf("Choose your branch")
    var isExpanded = mutableStateOf(false)

    var selectedPdfUrl = mutableStateOf("")
    var selectedPage = mutableStateOf(0)

    fun onBranchSelected(branch: String) {
        selectedBranch.value = branch
        isExpanded.value = false
    }

    fun toggleDropdown() {
        isExpanded.value = !isExpanded.value
    }

    fun closeDropdown() {
        isExpanded.value = false
    }

    // 🔥 MAIN FUNCTION
    fun loadTimetable() {
        val data = timetableList.find {
            it.branch == selectedBranch.value
        }

        selectedPdfUrl.value = data?.pdfUrl ?: ""
        selectedPage.value = data?.page ?: 0
    }
}