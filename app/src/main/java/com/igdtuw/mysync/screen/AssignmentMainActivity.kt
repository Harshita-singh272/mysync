package com.igdtuw.mysync.screen



import androidx.compose.runtime.*
import com.igdtuw.mysync.model.*

@Composable
fun MainScreen() {

    var showAdmin by remember { mutableStateOf(false) }

    val subjects = remember {
        mutableStateListOf(
            SubjectData("Soft Skills & Personality Development", mutableListOf(), mutableListOf()),
            SubjectData("Probability & Statistics", mutableListOf(), mutableListOf()),
            SubjectData("Mobile Application Development", mutableListOf(), mutableListOf()),
            SubjectData("Data Structures", mutableListOf(), mutableListOf()),
            SubjectData("Introduction to Data Science", mutableListOf(), mutableListOf()),
            SubjectData("Environmental Science", mutableListOf(), mutableListOf())
        )
    }

    if (showAdmin) {
        AdminAssignmentScreen(subjects) {
            showAdmin = false
        }
    } else {
        AssignmentScreen(subjects) {
            showAdmin = true
        }
    }
}