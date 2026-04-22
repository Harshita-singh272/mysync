package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TimetableViewModel : ViewModel() {

    // List of branches
    val branches = listOf(
        "Cse-1",
        "Cse-2",
        "Cse-3",
        "CseAi-1",
        "CseAi-2",
        "CseAi-3"
    )

    // Selected branch
    var selectedBranch = mutableStateOf("Choose your branch")
        private set

    // Dropdown state
    var isExpanded = mutableStateOf(false)
        private set

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
}