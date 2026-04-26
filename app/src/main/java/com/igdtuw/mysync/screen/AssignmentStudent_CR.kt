//package com.igdtuw.mysync.screen
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material3.*
//import androidx.compose.material3.AlertDialogDefaults.shape
//import androidx.compose.material3.ButtonDefaults.shape
//import androidx.compose.runtime.*
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.igdtuw.mysync.R
//import com.igdtuw.mysync.model.AssignmentItem
//import com.igdtuw.mysync.viewmodel.AssignmentViewModel
//import com.igdtuw.mysync.ui.theme.*
//import kotlinx.coroutines.launch
//
//
//@Composable
//fun AssignmentCRScreen(navController: NavController,
//                       viewModel: AssignmentViewModel,
//                       onEditClick: () -> Unit
//) {
//    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
//    val assignments by viewModel.assignments.collectAsState()
//    var assignmentToDelete by remember { mutableStateOf<AssignmentItem?>(null) }
//    val subjects = viewModel.subjects
//    val context = LocalContext.current
//    Scaffold(
//        snackbarHost = {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()   // 👈 FIX (NOT full screen)
//                    .padding(top = 40.dp),
//                contentAlignment = Alignment.TopCenter
//            ) {
//                SnackbarHost(hostState = snackbarHostState)
//            }
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    navController.navigate("cr_assignment")
//                },
//                containerColor = colorResource(id = R.color.dark_olive),
//                modifier= Modifier.border(color = colorResource(id = R.color.sage_gray), width = 2.dp, shape = RoundedCornerShape(15.dp))
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add",
//                    tint = colorResource(id = R.color.off_white)
//                )
//            }
//        }
//
//    ) { paddingValues ->
//        Box(modifier = Modifier.padding(paddingValues).background(sageGreen))
//        LazyColumn(modifier = Modifier.fillMaxSize().background(cream)) {
//
//            item {
//                Text(
//                    text = "ASSIGNMENT",
//                    style = MaterialTheme.typography.headlineMedium,
//                    color = olive,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//
//            items(subjects) { subject ->
//
//                var expanded by remember { mutableStateOf(false) }
//
//                val theoryList = assignments.filter {
//                    it.subject == subject && it.type == "theory"
//                }
//
//                val labList = assignments.filter {
//                    it.subject == subject && it.type == "lab"
//                }
//
//                Card(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth()
//                        .clickable { expanded = !expanded }
//                        .border(
//                            width = if (expanded) 3.dp else 0.dp,
//                            color = colorResource(id = R.color.sage_green),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    colors = CardDefaults.cardColors(
//                        containerColor = olive
//                    ),
//
//                ) {
//                    Column(modifier = Modifier.padding(12.dp)) {
//                        Row() {
//                            Text(
//                                subject, color = cream,
//                                fontFamily = FontFamily(Font(R.font.nunito_bold)),
//                                fontWeight = FontWeight.Bold,
//                                fontSize= 15.sp)
//                            Column(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalAlignment = Alignment.End
//                            ) {
//                                Text(
//                                    text = "Theory: ${theoryList.size}       Lab: ${labList.size}",
//                                    color = colorResource(id = R.color.dark_grey),
//                                    fontWeight = FontWeight.SemiBold,
//                                    fontFamily = FontFamily(Font(R.font.nunito_light))
//                                )
//                            }
//                        }
//                        if (expanded) {
//                            Column(modifier = Modifier.padding(vertical = 6.dp)) {
//                                Text("Theory", color =colorResource(id = R.color.sage_gray),
//                                    fontFamily = FontFamily(Font(R.font.merriweather_24pt_bold)),
//                                    fontSize = 15.sp)
//                                if (theoryList.isNotEmpty()) {
//                                    theoryList.forEach {
//
//                                        Column() {
//                                            AssignmentItemUI(it, context)
//                                            Row() {
//                                                Box(modifier = Modifier.size(30.dp).background(
//                                                    color = colorResource(id = R.color.off_white),
//                                                    shape = RoundedCornerShape(10.dp)
//                                                )) {
//                                                    IconButton(onClick = { viewModel.selectedAssignment.value = it
//                                                        navController.navigate("cr_assignment")}) {
//                                                        Icon(
//                                                            tint = colorResource(id = R.color.olive),
//                                                            imageVector = Icons.Default.Edit,
//                                                            contentDescription = "Edit"
//                                                        )
//                                                    }
//                                                }
//                                                Spacer(modifier = Modifier.width(10.dp))
//                                                Box(modifier = Modifier.size(30.dp).background(
//                                                    color = colorResource(id = R.color.off_white),
//                                                    shape = RoundedCornerShape(10.dp)
//                                                )) {
//                                                    IconButton(onClick = {
//                                                        assignmentToDelete = it
//                                                    }) {
//                                                        Icon(
//                                                            tint = colorResource(id = R.color.olive),
//                                                            imageVector = Icons.Default.Delete,
//                                                            contentDescription = "Delete"
//                                                        )
//                                                    }
//                                                }
//                                            }
//                                            Spacer(modifier = Modifier.height(5.dp))
//                                            HorizontalDivider(
//                                                thickness = 1.dp,
//                                                color = colorResource(id = R.color.light_sage_gray)
//                                            )
//                                        }
//                                    }
//
//                                } else {
//                                    Text(
//                                        "No Theory Assignment",
//                                        color = colorResource(id = R.color.sage_green)
//                                    )
//                                }
//                                Spacer(modifier = Modifier.height(10.dp))
//                                Text("Lab",color =colorResource(id = R.color.sage_gray),
//                                    fontFamily = FontFamily(Font(R.font.merriweather_24pt_bold)),
//                                    fontSize = 15.sp)
//                                if (labList.isNotEmpty()) {
//                                    labList.forEach {
//
//                                        Column() {
//                                            AssignmentItemUI(it, context)
//                                            Row() {
//                                                Box(modifier = Modifier.size(30.dp).background(
//                                                    color = colorResource(id = R.color.off_white),
//                                                    shape = RoundedCornerShape(10.dp)
//                                                )) {
//                                                    IconButton(onClick = {   viewModel.selectedAssignment.value = it
//                                                        navController.navigate("cr_assignment")}) {
//                                                        Icon(
//                                                            tint = colorResource(id = R.color.olive),
//                                                            imageVector = Icons.Default.Edit,
//                                                            contentDescription = "Edit"
//                                                        )
//                                                    }
//                                                }
//                                                Spacer(modifier = Modifier.width(10.dp))
//                                                Box(modifier = Modifier.size(30.dp).background(
//                                                    color = colorResource(id = R.color.off_white),
//                                                    shape = RoundedCornerShape(10.dp)
//                                                )) {
//                                                    IconButton(onClick = {
//                                                        assignmentToDelete = it
//                                                    }) {
//                                                        Icon(
//                                                            tint = colorResource(id = R.color.olive),
//                                                            imageVector = Icons.Default.Delete,
//                                                            contentDescription = "Delete"
//                                                        )
//                                                    }
//                                                }
//                                            }
//                                            Spacer(modifier = Modifier.height(5.dp))
//                                            HorizontalDivider(
//                                                thickness = 1.dp,
//                                                color = colorResource(id = R.color.light_sage_gray)
//                                            )
//                                        }
//                                    }
//                                } else {
//                                    Text(
//                                        "No Lab Assignment",
//                                        color = colorResource(id = R.color.sage_green)
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if (assignmentToDelete != null) {
//            AlertDialog(
//                onDismissRequest = {
//                    assignmentToDelete = null
//                },
//                title = {
//                    Text("Confirm Delete",
//                        fontFamily = FontFamily(Font(R.font.nunito_bold))
//                        )
//                },
//                text = {
//                    Text("Are you sure you want to delete this assignment?",
//                        fontFamily = FontFamily(Font(R.font.nunito_semibold)))
//                },
//                confirmButton = {
//                    TextButton(onClick = {
//                        viewModel.deleteAssignment(assignmentToDelete!!.id)
//                        assignmentToDelete = null
//                        scope.launch {
//                            snackbarHostState.showSnackbar("Assignment Deleted")
//                        }
//                    }) {
//                        Text("Delete")
//                    }
//                },
//                dismissButton = {
//                    TextButton(onClick = {
//                        assignmentToDelete = null
//                    }) {
//                        Text("Cancel")
//                    }
//                },
//                shape = RoundedCornerShape(20.dp),
//
//                modifier = Modifier.border(
//                    width = 2.dp,
//                    color = colorResource(id = R.color.purple_40),
//                    shape = RoundedCornerShape(20.dp)
//                ),
//
//                containerColor = colorResource(id = R.color.off_white),
//            )
//        }
//    }
//}
package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.igdtuw.mysync.R   // ✅ IMPORTANT
import com.igdtuw.mysync.model.AssignmentItem
import com.igdtuw.mysync.viewmodel.AssignmentViewModel
import com.igdtuw.mysync.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun AssignmentCRScreen(
    navController: NavController,
    viewModel: AssignmentViewModel,
    onEditClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val assignments by viewModel.assignments.collectAsState()
    var assignmentToDelete by remember { mutableStateOf<AssignmentItem?>(null) }

    val subjects = viewModel.subjects
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                SnackbarHost(hostState = snackbarHostState)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("cr_assignment")
                },
                containerColor = colorResource(id = R.color.dark_olive),
                modifier = Modifier.border(
                    color = colorResource(id = R.color.sage_gray),
                    width = 2.dp,
                    shape = RoundedCornerShape(15.dp)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = colorResource(id = R.color.off_white)
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(cream)
        ) {

            item {
                Text(
                    text = "ASSIGNMENT",
                    style = MaterialTheme.typography.headlineMedium,
                    color = olive,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(subjects) { subject ->

                var expanded by remember { mutableStateOf(false) }

                val theoryList = assignments.filter {
                    it.subject == subject && it.type == "theory"
                }

                val labList = assignments.filter {
                    it.subject == subject && it.type == "lab"
                }

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { expanded = !expanded }
                        .border(
                            width = if (expanded) 3.dp else 0.dp,
                            color = colorResource(id = R.color.sage_green),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = olive)
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                subject,
                                color = cream,
                                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )

                            Text(
                                text = "Theory: ${theoryList.size}   Lab: ${labList.size}",
                                color = colorResource(id = R.color.dark_grey),
                                fontFamily = FontFamily(Font(R.font.nunito_light))
                            )
                        }

                        if (expanded) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Theory",
                                color = colorResource(id = R.color.sage_gray),
                                fontFamily = FontFamily(Font(R.font.merriweather_24pt_bold))
                            )

                            theoryList.forEach {
                                AssignmentItemUI(it, context)

                                Row {
                                    IconButton(onClick = {
                                        viewModel.selectedAssignment.value = it
                                        navController.navigate("cr_assignment")
                                    }) {
                                        Icon(Icons.Default.Edit, "Edit")
                                    }

                                    IconButton(onClick = {
                                        assignmentToDelete = it
                                    }) {
                                        Icon(Icons.Default.Delete, "Delete")
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Lab",
                                color = colorResource(id = R.color.sage_gray)
                            )

                            labList.forEach {
                                AssignmentItemUI(it, context)

                                Row {
                                    IconButton(onClick = {
                                        viewModel.selectedAssignment.value = it
                                        navController.navigate("cr_assignment")
                                    }) {
                                        Icon(Icons.Default.Edit, "Edit")
                                    }

                                    IconButton(onClick = {
                                        assignmentToDelete = it
                                    }) {
                                        Icon(Icons.Default.Delete, "Delete")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // DELETE DIALOG
        if (assignmentToDelete != null) {
            AlertDialog(
                onDismissRequest = { assignmentToDelete = null },
                title = { Text("Confirm Delete") },
                text = { Text("Are you sure?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteAssignment(assignmentToDelete!!.id)
                        assignmentToDelete = null

                        scope.launch {
                            snackbarHostState.showSnackbar("Assignment Deleted")
                        }
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        assignmentToDelete = null
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}