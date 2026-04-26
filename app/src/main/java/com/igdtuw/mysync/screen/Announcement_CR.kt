package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.model.Announcement
import com.igdtuw.mysync.ui.theme.cream
import com.igdtuw.mysync.viewmodel.AnnouncementViewModel

@Composable
fun Announcement_CR(viewModel: AnnouncementViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val announcements = viewModel.announcements
    var selectedAnnouncement by remember { mutableStateOf<Announcement?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }

    if (showEditDialog && selectedAnnouncement != null) {
        var editTitle by remember { mutableStateOf(selectedAnnouncement!!.title) }
        var editDesc by remember { mutableStateOf(selectedAnnouncement!!.description) }

        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Edit Post") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editTitle,
                        onValueChange = { editTitle = it },
                        label = { Text("Title") })
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editDesc,
                        onValueChange = { editDesc = it },
                        label = { Text("Description") })
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.updateAnnouncement(selectedAnnouncement!!.id, editTitle, editDesc)
                    showEditDialog = false
                }) { Text("Save") }
            },
            dismissButton = { TextButton(onClick = { showEditDialog = false }) { Text("Cancel") } }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9F2)).padding(bottom= 20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = Color(0xFFA3B18A)
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 35.dp, bottom = 10.dp)
            ) {
                Text(
                    "CR Announcement Board",
                    fontWeight = FontWeight.Black,
                    fontSize = 28.sp,
                    modifier= Modifier.align(Alignment.CenterHorizontally),
                    color = colorResource(id = R.color.dark_olive)
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.border))
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Topic") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Details") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(12.dp))
                        Button(
                            onClick = {
                                viewModel.addAnnouncement(
                                    title,
                                    description,
                                    "General"
                                ); title = ""; description = ""
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.olive))
                        ) { Text("Post to Students") }
                    }
                }
            }
            items(announcements) { item ->
                var menuExpanded by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.title, fontWeight = FontWeight.Bold)
                            Text(item.description, fontSize = 12.sp, color = Color.Gray)
                        }
                        Box {
                            IconButton(onClick = {
                                menuExpanded = true
                            }) { Icon(Icons.Default.MoreVert, null) }
                            DropdownMenu(
                                expanded = menuExpanded,
                                onDismissRequest = { menuExpanded = false }) {
                                DropdownMenuItem(
                                    text = { Text("Edit") },
                                    onClick = {
                                        selectedAnnouncement = item; showEditDialog =
                                        true; menuExpanded = false
                                    })
                                DropdownMenuItem(
                                    text = { Text("Delete", color = Color.Red) },
                                    onClick = {
                                        viewModel.deleteAnnouncement(item.id); menuExpanded = false
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}
