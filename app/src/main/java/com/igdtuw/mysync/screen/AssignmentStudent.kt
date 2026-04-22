package com.igdtuw.mysync.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.igdtuw.mysync.model.*
import com.igdtuw.mysync.ui.theme.cream
import com.igdtuw.mysync.ui.theme.olive
import com.igdtuw.mysync.ui.theme.sageGreen
import com.igdtuw.mysync.viewmodel.AssignmentViewModel

@Composable
fun AssignmentScreen(
    viewModel: AssignmentViewModel = viewModel(),
    onEditClick: () -> Unit
) {
    val context = LocalContext.current
    val subjects by viewModel.subjects.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(cream)
    ) {
        LazyColumn {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(sageGreen.copy(alpha = 0.25f))
                        .padding(vertical = 14.dp, horizontal = 12.dp)
                ) {
                    Text(
                        text = "ASSIGNMENT",
                        color = olive,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
//            items(subject) { subject ->
//                var expanded by remember { mutableStateOf(false) }
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .clickable { expanded = !expanded }
//                        .animateContentSize(),
//                    colors = CardDefaults.cardColors(containerColor = olive)
//                ) {
//                    Column(modifier = Modifier.padding(12.dp)) {
//                        Text(
//                            text = subject.name,
//                            color = cream,
//                            style = MaterialTheme.typography.titleMedium
//                        )
//                        if (expanded) {
//                            Spacer(modifier = Modifier.height(8.dp))
//                            subject.theory.forEach {
//                                Text(
//                                    text = "• ${it.title}",
//                                    color = sageGreen,
//                                    modifier = Modifier
//                                        .padding(vertical = 4.dp)
//                                        .clickable {
//                                            context.startActivity(
//                                                Intent(
//                                                    Intent.ACTION_VIEW,
//                                                    Uri.parse(it.link)
//                                                )
//                                            )
//                                        }
//                                )
//                            }
//                        }
//                    }
//                }
//            }
            items(subjects) { subject ->
                var expanded by remember(subject.name) { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { expanded = !expanded }
                        .animateContentSize(),
                    colors = CardDefaults.cardColors(containerColor = olive)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = subject.name,
                            color = cream,
                            style = MaterialTheme.typography.titleMedium
                        )

                        if (expanded) {
                            Spacer(modifier = Modifier.height(8.dp))
                            if (subject.theory.isNotEmpty()) {
                                Text("Theory", color = sageGreen)

                                subject.theory.forEach {
                                    Text(
                                        text = "• ${it.title}",
                                        color = cream,
                                        modifier = Modifier
                                            .padding(vertical = 4.dp)
                                            .clickable {
                                                context.startActivity(
                                                    Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
                                                )
                                            }
                                    )
                                }
                            }
                            if (subject.lab.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Lab", color = sageGreen)

                                subject.lab.forEach {
                                    Text(
                                        text = "• ${it.title}",
                                        color = cream,
                                        modifier = Modifier
                                            .padding(vertical = 4.dp)
                                            .clickable {
                                                context.startActivity(
                                                    Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
                                                )
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
//        FloatingActionButton(
//            onClick = onEditClick,
//            containerColor = sageGreen,
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//        ) {
//            Text("+", color = cream)
//        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun AssignmentScreenPreview() {
//    AssignmentScreen(
//        subjects = listOf(
//            SubjectData(
//                name = "Mathematics",
//                theory = mutableListOf(
//                    AssignmentItem(title = "Assignment 1", link = "https://example.com"),
//                    AssignmentItem(title = "Assignment 2", link = "https://example.com")
//                ),
//                lab = mutableListOf(
//                    AssignmentItem(title = "Lab 1 - Experiment", link = "https://example.com")
//                )
//            ),
//            SubjectData(
//                name = "Physics",
//                theory = mutableListOf(
//                    AssignmentItem(title = "Wave Optics", link = "https://example.com")
//                ),
//                lab = mutableListOf()
//            )
//        ),
//        onEditClick = {}
//    )
//}