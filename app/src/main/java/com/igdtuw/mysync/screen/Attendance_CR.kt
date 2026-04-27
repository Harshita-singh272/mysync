package com.igdtuw.mysync.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.igdtuw.mysync.R
import com.igdtuw.mysync.model.AttendanceRecord
import com.igdtuw.mysync.ui.theme.components.AppCard
import com.igdtuw.mysync.ui.theme.components.AttendanceToggleButton
import com.igdtuw.mysync.viewmodel.AttendanceViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Attendance_CR(viewModel: AttendanceViewModel = viewModel()) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedSubject by remember { mutableStateOf("Select Subject") }
    var expanded by remember { mutableStateOf(false) }
    var showAddSubjectDialog by remember { mutableStateOf(false) }
    var newSubjectName by remember { mutableStateOf("") }
    val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

    val filteredStudents = viewModel.studentList.filter {
        it.studentName.contains(searchQuery, ignoreCase = true)
    }

    if (showAddSubjectDialog) {
        AlertDialog(
            onDismissRequest = { showAddSubjectDialog = false },
            title = { Text("New Subject", fontWeight = FontWeight.Bold) },
            text = {
                OutlinedTextField(
                    value = newSubjectName,
                    onValueChange = { newSubjectName = it },
                    label = { Text("Enter Subject Name") },
                    shape = RoundedCornerShape(12.dp)
                )
            },
            confirmButton = {
                Button(onClick = {
                    if (newSubjectName.isNotBlank()) {
                        viewModel.addSubject(newSubjectName)
                        showAddSubjectDialog = false
                        newSubjectName = ""
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.olive))) { Text("Add") }
            },
            dismissButton = { TextButton(onClick = { showAddSubjectDialog = false }) { Text("Cancel") } }
        )
    }

    Scaffold(
        topBar = {
            Surface(shadowElevation = 6.dp, color = colorResource(id = R.color.olive), modifier = Modifier.fillMaxWidth()) {
//
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(
                            color = Color(0xFFA3B18A)
                        ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 35.dp, bottom = 10.dp, end=10.dp)
                    ) {
                        Text(
                            "Manage Attendance",
                            fontWeight = FontWeight.Black,
                            fontSize = 32.sp,
//                            modifier= Modifier.align(Alignment.CenterHorizontally),
                            color = colorResource(id = R.color.dark_olive)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = searchQuery, onValueChange = { searchQuery = it },
                            placeholder = { Text("Search by student name...", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.White, RoundedCornerShape(12.dp)),
                            leadingIcon = { Icon(Icons.Default.Search, null, tint = Color.Gray) },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )
                    }
                }
                HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.border))
            }
        },
        bottomBar = {
            Surface(shadowElevation = 10.dp) {
                Button(
                    onClick = {
                        if (selectedSubject != "Select Subject") {
                            viewModel.uploadAttendance(selectedSubject, currentDate)
                            Toast.makeText(context, "Attendance uploaded!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    enabled = selectedSubject != "Select Subject",
                    modifier = Modifier.padding(20.dp).fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.olive))
                ) { Text("Submit Attendance", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(Color(0xFFF7F9F2))) {
            Card(modifier = Modifier.padding(16.dp).fillMaxWidth().shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.weight(1f)) {
                        TextButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                            Text(selectedSubject, color = Color.DarkGray)
                            Icon(Icons.Default.ArrowDropDown, null, tint = Color.Gray)
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            viewModel.subjects.forEach { name ->
                                DropdownMenuItem(text = { Text(name) }, onClick = { selectedSubject = name; expanded = false })
                            }
                        }
                    }
                    IconButton(onClick = { showAddSubjectDialog = true }) { Icon(Icons.Default.Add, null, tint = colorResource(id = R.color.olive)) }
                }
            }

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(onClick = { viewModel.markAllPresent(true) }) { Text("Mark All Present") }
                TextButton(onClick = { viewModel.markAllPresent(false) }) { Text("Clear All", color = Color.Gray) }
            }

            LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp), contentPadding = PaddingValues(16.dp)) {
                itemsIndexed(filteredStudents) { _, student ->
                    AttendanceCard(student) { isChecked ->
                        val idx = viewModel.studentList.indexOfFirst { it.email == student.email }
                        if (idx != -1) viewModel.studentList[idx] = student.copy(isPresent = isChecked)
                    }
                }
            }
        }
    }
}

@Composable
fun AttendanceCard(student: AttendanceRecord, onStatusChange: (Boolean) -> Unit) {
    AppCard {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(student.studentName, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                Text(student.enrollmentNo, fontSize = 12.sp, color = Color.Gray)
            }
            AttendanceToggleButton(isSelected = student.isPresent, label = "P", activeColor = Color(0xFF6F7B5E), onClick = { onStatusChange(true) })
            Spacer(modifier = Modifier.width(12.dp))
            AttendanceToggleButton(isSelected = !student.isPresent, label = "A", activeColor = Color(0xFFD9534F), onClick = { onStatusChange(false) })
        }
    }
}