package com.igdtuw.mysync.screen

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.igdtuw.mysync.viewmodel.AssignmentViewModel

@Composable
fun MainScreen() {

    var showAdmin by remember { mutableStateOf(false) }
    val vm: AssignmentViewModel = viewModel()

    if (showAdmin) {
        AdminAssignmentScreen(
            viewModel = vm,
            onBack = { showAdmin = false }
        )
    } else {
        AssignmentScreen(
            viewModel = vm,
            onEditClick = { showAdmin = true }
        )
    }
}