package com.igdtuw.mysync.screen

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate("cr_assignment") },
                containerColor = olive,
                contentColor = Color.White,
                shape = CircleShape,
                icon = { Icon(Icons.Default.Add, "Add") },
                text = { Text("New Post") }
            )
        },
        containerColor = cream
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Batch Assignments",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = olive
                    )
                    Text(
                        text = "Managing CSE '29 Curriculum",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            items(subjects) { subject ->
                var expanded by remember { mutableStateOf(false) }
                val theoryList = assignments.filter { it.subject == subject && it.type == "theory" }
                val labList = assignments.filter { it.subject == subject && it.type == "lab" }

                Surface(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Column(modifier = Modifier.clickable { expanded = !expanded }.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = subject,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray
                                )
                                Text(
                                    text = "${theoryList.size} Theory • ${labList.size} Lab",
                                    fontSize = 12.sp,
                                    color = olive
                                )
                            }
                            Icon(
                                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }

                        AnimatedVisibility(
                            visible = expanded,
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ) {
                            Column(modifier = Modifier.padding(top = 16.dp)) {
                                SectionHeader("Theory Materials")
                                theoryList.forEach { item ->
                                    AssignmentActionRow(item, context, viewModel, navController) {
                                        assignmentToDelete = it
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                SectionHeader("Lab Assignments")
                                labList.forEach { item ->
                                    AssignmentActionRow(item, context, viewModel, navController) {
                                        assignmentToDelete = it
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (assignmentToDelete != null) {
            AlertDialog(
                onDismissRequest = { assignmentToDelete = null },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteAssignment(assignmentToDelete!!.id)
                            assignmentToDelete = null
                            scope.launch { snackbarHostState.showSnackbar("Removed from Batch list") }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828))
                    ) { Text("Delete") }
                },
                dismissButton = {
                    TextButton(onClick = { assignmentToDelete = null }) { Text("Cancel") }
                },
                title = { Text("Delete Assignment?") },
                text = { Text("Classmates will no longer be able to see this link.") },
                shape = RoundedCornerShape(24.dp),
                containerColor = Color.White
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title.uppercase(),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun AssignmentActionRow(
    item: AssignmentItem,
    context: Context,
    viewModel: AssignmentViewModel,
    navController: NavController,
    onDelete: (AssignmentItem) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                AssignmentItemUI(item, context)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cream.copy(alpha = 0.4f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                viewModel.selectedAssignment.value = item
                                navController.navigate("cr_assignment")
                            },
                        shadowElevation = 1.dp
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = olive,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFFFF1F1),
                        modifier = Modifier
                            .size(36.dp)
                            .clickable { onDelete(item) },
                        shadowElevation = 1.dp
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color(0xFFC62828),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AssignmentItemUI(item: AssignmentItem, context: android.content.Context) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                if (item.link.isNotEmpty()) {
                    try {
                        val intent = android.content.Intent(
                            android.content.Intent.ACTION_VIEW,
                            android.net.Uri.parse(item.link)
                        )
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        android.widget.Toast.makeText(context, "Could not open link", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            }
    ) {
        Text(
            text = item.title,
            color = olive,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        if (item.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.description,
                color = Color.DarkGray.copy(alpha = 0.8f),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}