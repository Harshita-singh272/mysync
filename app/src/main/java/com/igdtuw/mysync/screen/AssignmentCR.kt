package com.igdtuw.mysync.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.AssignmentViewModel
import com.igdtuw.mysync.model.AssignmentItem
import com.igdtuw.mysync.ui.theme.*
import kotlinx.coroutines.launch

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
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isLab by remember { mutableStateOf(false) }
    LaunchedEffect(selectedAssignment) {
        if (selectedAssignment != null) {
            title = selectedAssignment!!.title
            link = selectedAssignment!!.link
            description = selectedAssignment!!.description
            selectedSubject = selectedAssignment!!.subject
            isLab = selectedAssignment!!.type == "lab"
        }
    }
    Scaffold(
        snackbarHost = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(top= 20.dp),
                contentAlignment = Alignment.TopCenter   // 👈 THIS is key
            ) {
                SnackbarHost(hostState = snackbarHostState)
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
//                .padding(paddingValues)
                .fillMaxSize()
                .background(cream)
                .padding(16.dp)
        ) {

        Text("CR PANEL", style = MaterialTheme.typography.headlineMedium, color = olive)

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .border(2.dp, color = colorResource(id = R.color.sage_green), shape = RoundedCornerShape(10.dp))
        ) {
            Row() {

                IconButton(onClick = { expanded = true }) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Subjects",
                        modifier = Modifier.padding(5.dp),
                        tint = colorResource(id = R.color.olive)
                    )
                }
                Text(
                    text = selectedSubject,
                    modifier = Modifier.padding(start = 5.dp)
                        .align(Alignment.CenterVertically),
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.dark_grey)
                )

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

        Spacer(modifier = Modifier.height(16.dp))

        // TYPE
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = !isLab, onClick = { isLab = false })
            Text("Theory")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(selected = isLab, onClick = { isLab = true })
            Text("Lab")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        OutlinedTextField(value = link, onValueChange = { link = it }, label = { Text("Link") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })

        Spacer(modifier = Modifier.height(16.dp))

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

                        scope.launch {
                            snackbarHostState.showSnackbar("Assignment Updated")
                        }

                    } else {
                        viewModel.addAssignment(item)

                        scope.launch {
                            snackbarHostState.showSnackbar("Assignment Added")
                        }
                    }

                    viewModel.selectedAssignment.value = null
                    title = ""
                    link = ""
                    description = ""
                }
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.bright_olive))
        ) {
            Text(
                if (selectedAssignment != null) "Update Assignment"
                else "Add Assignment",
                color = colorResource(id = R.color.off_white))
        }

        Button(onClick = onBack,
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.bright_olive))) {
            Text("Back",
                color = colorResource(id = R.color.off_white))
        }
    }
}
}