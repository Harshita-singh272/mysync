package com.igdtuw.mysync.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.AssignmentViewModel
import com.igdtuw.mysync.model.AssignmentItem
import com.igdtuw.mysync.ui.theme.cream
import com.igdtuw.mysync.ui.theme.olive
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAssignmentScreen(
    viewModel: AssignmentViewModel,
    onBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val selectedAssignment by viewModel.selectedAssignment.collectAsState()
    val subjects = viewModel.subjects

    var expanded by remember { mutableStateOf(false) }
    var selectedSubject by remember { mutableStateOf(subjects[0]) }
    var title by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isLab by remember { mutableStateOf(false) }

    LaunchedEffect(selectedAssignment) {
        selectedAssignment?.let {
            title = it.title
            link = it.link
            description = it.description
            selectedSubject = it.subject
            isLab = it.type == "lab"
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("CR Control Panel", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = olive
                )
            )
        },
        containerColor = cream
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                shadowElevation = 8.dp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = if (selectedAssignment != null) "Edit Assignment" else "New Assignment",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = olive
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Select Subject", fontSize = 12.sp, color = Color.Gray)
                    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        OutlinedCard(
                            onClick = { expanded = true },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(selectedSubject, color = Color.Black)
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = olive)
                            }
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            subjects.forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        selectedSubject = it
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilterChip(
                            selected = !isLab,
                            onClick = { isLab = false },
                            label = { Text("Theory") },
                            colors = FilterChipDefaults.filterChipColors(selectedContainerColor = olive, selectedLabelColor = Color.White)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        FilterChip(
                            selected = isLab,
                            onClick = { isLab = true },
                            label = { Text("Lab Activity") },
                            colors = FilterChipDefaults.filterChipColors(selectedContainerColor = olive, selectedLabelColor = Color.White)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Topic Name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = link,
                        onValueChange = { link = it },
                        label = { Text("Drive/Submission Link") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Additional Notes") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (title.isNotBlank() && link.isNotBlank()) {
                                val item = AssignmentItem(
                                    id = selectedAssignment?.id ?: "",
                                    title = title,
                                    link = link,
                                    description = description,
                                    subject = selectedSubject,
                                    type = if (isLab) "lab" else "theory"
                                )

                                if (selectedAssignment != null) {
                                    viewModel.updateAssignment(item)
                                    scope.launch { snackbarHostState.showSnackbar("Updated Successfully") }
                                } else {
                                    viewModel.addAssignment(item)
                                    scope.launch { snackbarHostState.showSnackbar("Published to Batch") }
                                }

                                viewModel.selectedAssignment.value = null
                                title = ""; link = ""; description = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = olive)
                    ) {
                        Text(
                            if (selectedAssignment != null) "Update Now" else "Publish Assignment",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Updating will notify all CSE '29 students.",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}