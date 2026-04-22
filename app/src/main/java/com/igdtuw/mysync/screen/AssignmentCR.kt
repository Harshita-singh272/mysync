package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igdtuw.mysync.model.SubjectData
import com.igdtuw.mysync.ui.theme.cream
import com.igdtuw.mysync.ui.theme.offWhite
import com.igdtuw.mysync.ui.theme.olive
import com.igdtuw.mysync.ui.theme.sageGreen
import com.igdtuw.mysync.model.AssignmentItem
import com.igdtuw.mysync.ui.theme.MySyncTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.AssignmentViewModel
//@Composable
//fun AdminAssignmentScreen(
//    viewModel: AssignmentViewModel,
//    onBack: () -> Unit
//) {
//    val subjects by viewModel.subjects.collectAsState()
//
//    var selectedIndex by remember { mutableStateOf(0) }
//    var assignmentTitle by remember { mutableStateOf("") }
//    var assignmentLink by remember { mutableStateOf("") }
//    var isLabSelected by remember { mutableStateOf(false) }
//    var showTitleDialog by remember { mutableStateOf(false) }
//    var showLinkDialog by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(cream)
//    ) {
//
//        Button(onClick = onBack) {
//            Text("Back")
//        }
//
//        subjects.forEachIndexed { index, subject ->
//            Row {
//                RadioButton(
//                    selected = selectedIndex == index,
//                    onClick = { selectedIndex = index }
//                )
//                Text(subject.name)
//            }
//        }
//
//        Button(onClick = {
//            if (assignmentTitle.isNotEmpty() && assignmentLink.isNotEmpty()) {
//
//                val newItem = AssignmentItem(assignmentTitle, assignmentLink)
//
//                viewModel.addAssignment(
//                    subjectName = subjects[selectedIndex].name,
//                    item = newItem
//                )
//
//                assignmentTitle = ""
//                assignmentLink = ""
//            }
//        }) {
//            Text("Add Assignment")
//        }
////    }
////}
////fun AdminAssignmentScreen(
////    subjects: MutableList<SubjectData>,
////    onBack: () -> Unit
////) {
////    var selectedIndex by remember { mutableStateOf(0) }
////    var assignmentTitle by remember { mutableStateOf("") }
////    var assignmentLink by remember { mutableStateOf("") }
////    var isLabSelected by remember { mutableStateOf(false) }
////    var showTitleDialog by remember { mutableStateOf(false) }
////    var showLinkDialog by remember { mutableStateOf(false) }
////
////    Column(
////        modifier = Modifier
////            .fillMaxSize()
////            .background(cream)
////    ) {
////        Box(
////            modifier = Modifier
////                .fillMaxWidth()
////                .background(sageGreen.copy(alpha = 0.25f))
////                .padding(16.dp)
////        ) {
////            Text("CR PANEL", color = olive, style = MaterialTheme.typography.headlineMedium)
////        }
////
////        Column(modifier = Modifier.padding(16.dp)) {
////
////            Spacer(modifier = Modifier.height(16.dp))
////
////            Text("Select Subject", color = olive)
////
////            subjects.forEachIndexed { index, subject ->
////                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
////                    RadioButton(
////                        selected = selectedIndex == index,
////                        onClick = { selectedIndex = index }
////                    )
////                    Text(subject.name, color = olive)
////                }
////            }
////
////            Spacer(modifier = Modifier.height(20.dp))
////
////            Text("Assignment Type", color = olive)
////
////            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
////                RadioButton(
////                    selected = !isLabSelected,
////                    onClick = { isLabSelected = false }
////                )
////                Text("Theory", color = olive)
////
////                Spacer(modifier = Modifier.width(16.dp))
////
////                RadioButton(
////                    selected = isLabSelected,
////                    onClick = { isLabSelected = true }
////                )
////                Text("Lab", color = olive)
////            }
////
////            Spacer(modifier = Modifier.height(20.dp))
////
////            Text("Create Assignment", color = sageGreen)
////
////            Spacer(modifier = Modifier.height(10.dp))
////
////            // TITLE INPUT
////            Box(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .background(offWhite)
////                    .clickable { showTitleDialog = true }
////                    .padding(12.dp)
////            ) {
////                Text(
////                    text = if (assignmentTitle.isEmpty()) "Tap to set title" else assignmentTitle,
////                    color = olive
////                )
////            }
////
////            Spacer(modifier = Modifier.height(10.dp))
////
////            // LINK INPUT
////            Box(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .background(offWhite)
////                    .clickable { showLinkDialog = true }
////                    .padding(12.dp)
////            ) {
////                Text(
////                    text = if (assignmentLink.isEmpty()) "Tap to set link" else assignmentLink,
////                    color = olive
////                )
////            }
////
////            Spacer(modifier = Modifier.height(20.dp))
////
////            Button(
////                onClick = {
////                    if (assignmentTitle.isNotEmpty() && assignmentLink.isNotEmpty()) {
////                        val newItem = AssignmentItem(assignmentTitle, assignmentLink)
////                        if (isLabSelected) {
////                            subjects[selectedIndex].lab.add(newItem)
////                        } else {
////                            subjects[selectedIndex].theory.add(newItem)
////                        }
////                        assignmentTitle = ""
////                        assignmentLink = ""
////                    }
////                },
////                colors = ButtonDefaults.buttonColors(containerColor = sageGreen)
////            ) {
////                Text("Add Assignment")
////            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//
//            // TITLE DIALOG
//            if (showTitleDialog) {
//                AlertDialog(
//                    onDismissRequest = { showTitleDialog = false },
//                    confirmButton = {
//                        TextButton(onClick = { showTitleDialog = false }) {
//                            Text("Done")
//                        }
//                    },
//                    title = { Text("Enter Title") },
//                    text = {
//                        TextField(
//                            value = assignmentTitle,
//                            onValueChange = { assignmentTitle = it },
//                            singleLine = true
//                        )
//                    }
//                )
//            }
//
//            // LINK DIALOG
//            if (showLinkDialog) {
//                AlertDialog(
//                    onDismissRequest = { showLinkDialog = false },
//                    confirmButton = {
//                        TextButton(onClick = { showLinkDialog = false }) {
//                            Text("Done")
//                        }
//                    },
//                    title = { Text("Enter Link") },
//                    text = {
//                        TextField(
//                            value = assignmentLink,
//                            onValueChange = { assignmentLink = it },
//                            singleLine = true
//                        )
//                    }
//                )
//            }
//        }
//    }
@Composable
fun AdminAssignmentScreen(
    viewModel: AssignmentViewModel,
    onBack: () -> Unit
) {
    val subjects by viewModel.subjects.collectAsState()

    var selectedIndex by remember { mutableStateOf(0) }
    var assignmentTitle by remember { mutableStateOf("") }
    var assignmentLink by remember { mutableStateOf("") }
    var isLabSelected by remember { mutableStateOf(false) }
    var showTitleDialog by remember { mutableStateOf(false) }
    var showLinkDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(cream)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(sageGreen.copy(alpha = 0.25f))
                .padding(16.dp)
        ) {
            Text("CR PANEL", color = olive, style = MaterialTheme.typography.headlineMedium)
        }

        Column(modifier = Modifier.padding(16.dp)) {



            Spacer(modifier = Modifier.height(16.dp))

            Text("Select Subject", color = olive)
            Syllabusd()
            subjects.forEachIndexed { index, subject ->
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                    Text(subject.name, color = olive)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Assignment Type", color = olive)

            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                RadioButton(
                    selected = !isLabSelected,
                    onClick = { isLabSelected = false }
                )
                Text("Theory", color = olive)

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = isLabSelected,
                    onClick = { isLabSelected = true }
                )
                Text("Lab", color = olive)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Create Assignment", color = sageGreen)

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(offWhite)
                    .clickable { showTitleDialog = true }
                    .padding(12.dp)
            ) {
                Text(
                    text = if (assignmentTitle.isEmpty()) "Tap to set title" else assignmentTitle,
                    color = olive
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // LINK INPUT
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(offWhite)
                    .clickable { showLinkDialog = true }
                    .padding(12.dp)
            ) {
                Text(
                    text = if (assignmentLink.isEmpty()) "Tap to set link" else assignmentLink,
                    color = olive
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (assignmentTitle.isNotEmpty() && assignmentLink.isNotEmpty()) {

                        val newItem = AssignmentItem(assignmentTitle, assignmentLink)

                        viewModel.addAssignment(
                            subjectName = subjects[selectedIndex].name,
                            item = newItem,
                            isLab = isLabSelected
                        )

                        assignmentTitle = ""
                        assignmentLink = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = sageGreen)
            ) {
                Text("Add Assignment")
            }
            Button(onClick = onBack,  colors = ButtonDefaults.buttonColors(containerColor = sageGreen)) {
                Text("Back")
            }
            Spacer(modifier = Modifier.height(20.dp))

            // TITLE DIALOG
            if (showTitleDialog) {
                AlertDialog(
                    onDismissRequest = { showTitleDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showTitleDialog = false }) {
                            Text("Done")
                        }
                    },
                    title = { Text("Enter Title") },
                    text = {
                        TextField(
                            value = assignmentTitle,
                            onValueChange = { assignmentTitle = it },
                            singleLine = true
                        )
                    }
                )
            }
            if (showLinkDialog) {
                AlertDialog(
                    onDismissRequest = { showLinkDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showLinkDialog = false }) {
                            Text("Done")
                        }
                    },
                    title = { Text("Enter Link") },
                    text = {
                        TextField(
                            value = assignmentLink,
                            onValueChange = { assignmentLink = it },
                            singleLine = true
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Syllabusd(){
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectsubject by remember {
        mutableStateOf("Please choose your subject")
    }
    Box(
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth()
            .border(2.dp, color = colorResource(id = R.color.sage_green), shape = RoundedCornerShape(10.dp))
    ){
        Text(
            text = selectsubject,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 16.sp,
            color = colorResource(id= R.color.dark_grey)
        )
        IconButton(onClick = {expanded = !expanded }) {
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Subjects",
                modifier = Modifier
                    .padding(10.dp),
                tint = colorResource(id = R.color.olive)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Mobile Application Development") },
                onClick = {
                    expanded = false
                    selectsubject = "Mobile Application Development"
                }
            )
            DropdownMenuItem(
                text = { Text("Probability and Statistics") },
                onClick = {
                    expanded = false
                    selectsubject = "Probability and Statistics"
                }
            )
            DropdownMenuItem(
                text = { Text("Environment science") },
                onClick = {
                    expanded = false
                    selectsubject = "Environment science"
                }
            )
            DropdownMenuItem(
                text = { Text("Soft skills and personality development") },
                onClick = {
                    expanded = false
                    selectsubject = "Soft skills and personality development"
                }
            )
            DropdownMenuItem(
                text = { Text("Introduction to data science") },
                onClick = {
                    expanded = false
                    selectsubject = "Introduction to data science"
                }
            )
            DropdownMenuItem(
                text = { Text("Data Structure") },
                onClick = {
                    expanded = false
                    selectsubject = "Data Structure"
                }
            )
        }
    }

}