package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

@Composable
fun AdminAssignmentScreen(
    subjects: MutableList<SubjectData>,
    onBack: () -> Unit
) {
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

            // TITLE INPUT
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
                        if (isLabSelected) {
                            subjects[selectedIndex].lab.add(newItem)
                        } else {
                            subjects[selectedIndex].theory.add(newItem)
                        }
                        assignmentTitle = ""
                        assignmentLink = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = sageGreen)
            ) {
                Text("Add Assignment")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = olive)
            ) {
                Text("Back", color = cream)
            }
        }
    }

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

    // LINK DIALOG
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

@Preview(showBackground = true, backgroundColor = 0xFF888888)
@Composable
fun AdminAssignmentScreenPreview() {
    MySyncTheme {
        val sampleSubjects = remember {
            mutableListOf(
                SubjectData("Maths", mutableListOf(), mutableListOf()),
                SubjectData("Physics", mutableListOf(), mutableListOf())
            )
        }
        AdminAssignmentScreen(
            subjects = sampleSubjects,
            onBack = {}
        )
    }
}