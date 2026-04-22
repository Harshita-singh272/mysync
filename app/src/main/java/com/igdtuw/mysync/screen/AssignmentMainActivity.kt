package com.igdtuw.mysync.screen

//
//
//import androidx.compose.runtime.*
//import com.igdtuw.mysync.model.*
//
//@Composable
//fun MainScreen() {
//
//    var showAdmin by remember { mutableStateOf(false) }
//
//    val subjects = remember {
//        mutableStateListOf(
//            SubjectData("Soft Skills & Personality Development", mutableListOf(), mutableListOf()),
//            SubjectData("Probability & Statistics", mutableListOf(), mutableListOf()),
//            SubjectData("Mobile Application Development", mutableListOf(), mutableListOf()),
//            SubjectData("Data Structures", mutableListOf(), mutableListOf()),
//            SubjectData("Introduction to Data Science", mutableListOf(), mutableListOf()),
//            SubjectData("Environmental Science", mutableListOf(), mutableListOf())
//        )
//    }
//
//    if (showAdmin) {
//        AdminAssignmentScreen(subjects) {
//            showAdmin = false
//        }
//    } else {
//        AssignmentScreen(subjects) {
//            showAdmin = true
//        }
//    }
//}
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.igdtuw.mysync.model.SubjectData
import com.igdtuw.mysync.viewmodel.AssignmentViewModel

@Composable
fun MainScreen() {

    var showAdmin by remember { mutableStateOf(false) }

    val vm: AssignmentViewModel = viewModel()

    // Initialize data only once
    LaunchedEffect(Unit) {
        if (vm.subjects.value.isEmpty()) {
            vm.setSubjects(
                listOf(
                    SubjectData(
                        "Soft Skills & Personality Development",
                        mutableListOf(),
                        mutableListOf()
                    ),
                    SubjectData("Probability & Statistics", mutableListOf(), mutableListOf()),
                    SubjectData("Mobile Application Development", mutableListOf(), mutableListOf()),
                    SubjectData("Data Structures", mutableListOf(), mutableListOf()),
                    SubjectData("Introduction to Data Science", mutableListOf(), mutableListOf()),
                    SubjectData("Environmental Science", mutableListOf(), mutableListOf())
                )
            )
        }
    }

    if (showAdmin) {
        AdminAssignmentScreen(
            viewModel = vm,
            onBack = {showAdmin = false}
        )
    } else {
        AssignmentScreen(
            viewModel = vm,
            onEditClick = { showAdmin = true }
        )
    }
}