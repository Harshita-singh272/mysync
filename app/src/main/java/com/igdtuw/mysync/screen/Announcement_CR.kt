//package com.igdtuw.mysync.screen
//
//import android.content.Context
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.igdtuw.mysync.R
//import com.igdtuw.mysync.model.Announcement
//import com.igdtuw.mysync.ui.theme.cream
//import com.igdtuw.mysync.viewmodel.AnnouncementViewModel
//
//@Composable
//fun Announcement_CR(viewModel: AnnouncementViewModel) {
//    var title by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    val announcements = viewModel.announcements
//    var selectedAnnouncement by remember { mutableStateOf<Announcement?>(null) }
//    var showEditDialog by remember { mutableStateOf(false) }
//    val context = LocalContext.current
//    val sharedPref = context.getSharedPreferences("MySyncPrefs", Context.MODE_PRIVATE)
//    if (showEditDialog && selectedAnnouncement != null) {
//        var editTitle by remember { mutableStateOf(selectedAnnouncement!!.title) }
//        var editDesc by remember { mutableStateOf(selectedAnnouncement!!.description) }
//
//        AlertDialog(
//            onDismissRequest = { showEditDialog = false },
//            title = { Text("Edit Post") },
//            text = {
//                Column {
//                    OutlinedTextField(
//                        value = editTitle,
//                        onValueChange = { editTitle = it },
//                        label = { Text("Title") })
//                    Spacer(Modifier.height(8.dp))
//                    OutlinedTextField(
//                        value = editDesc,
//                        onValueChange = { editDesc = it },
//                        label = { Text("Description") })
//                }
//            },
//            confirmButton = {
//                Button(onClick = {
//                    viewModel.updateAnnouncement(selectedAnnouncement!!.id, editTitle, editDesc)
//                    showEditDialog = false
//                }) { Text("Save") }
//            },
//            dismissButton = { TextButton(onClick = { showEditDialog = false }) { Text("Cancel") } }
//        )
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF7F9F2)).padding(bottom= 20.dp)
//    ) {
//        Box(
//            modifier = Modifier.fillMaxWidth()
//                .background(
//                    color = Color(0xFFA3B18A)
//                ),
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 10.dp, top = 35.dp, bottom = 10.dp)
//            ) {
//                Text(
//                    "CR Announcement Board",
//                    fontWeight = FontWeight.Black,
//                    fontSize = 30.sp,
//                    color = colorResource(id = R.color.dark_olive)
//                )
//            }
//        }
//        HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.border))
//        LazyColumn(
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            item {
//                Card(
//                    modifier = Modifier.fillMaxWidth().shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        OutlinedTextField(
//                            value = title,
//                            onValueChange = { title = it },
//                            label = { Text("Topic") },
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                        Spacer(Modifier.height(8.dp))
//                        OutlinedTextField(
//                            value = description,
//                            onValueChange = { description = it },
//                            label = { Text("Details") },
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                        Spacer(Modifier.height(12.dp))
//                        Button(
//                            onClick = {
//                                viewModel.addAnnouncement(
//                                    title,
//                                    description,
//                                    "General"
//                                ); title = ""; description = ""
//
//                                sharedPref.edit()
//                                    .putLong("post_time", System.currentTimeMillis())
//                            },
//                            modifier = Modifier.fillMaxWidth(),
//                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.olive))
//                        ) { Text("Post to Students") }
//                    }
//                }
//            }
//            items(announcements) { item ->
//                var menuExpanded by remember { mutableStateOf(false) }
//                Card(
//                    modifier = Modifier.fillMaxWidth().shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
//                    Row(
//                        modifier = Modifier.padding(16.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Column(modifier = Modifier.weight(1f)) {
//                            val Posttime = sharedPref.getLong("post_time", 0L)
//                            Text(Posttime.toString(), fontSize = 15.sp, color = Color.Black)
//                            Text(item.title, fontWeight = FontWeight.Bold)
//                            Text(item.description, fontSize = 12.sp, color = Color.Gray)
//                        }
//                        Box {
//                            IconButton(onClick = {
//                                menuExpanded = true
//                            }) { Icon(Icons.Default.MoreVert, null) }
//                            DropdownMenu(
//                                expanded = menuExpanded,
//                                onDismissRequest = { menuExpanded = false }) {
//                                DropdownMenuItem(
//                                    text = { Text("Edit") },
//                                    onClick = {
//                                        selectedAnnouncement = item; showEditDialog =
//                                        true; menuExpanded = false
//                                    })
//                                DropdownMenuItem(
//                                    text = { Text("Delete", color = Color.Red) },
//                                    onClick = {
//                                        viewModel.deleteAnnouncement(item.id); menuExpanded = false
//                                    })
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
package com.igdtuw.mysync.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import com.igdtuw.mysync.R
import com.igdtuw.mysync.model.Announcement
import com.igdtuw.mysync.ui.theme.cream
import com.igdtuw.mysync.viewmodel.AnnouncementViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Announcement_CR(viewModel: AnnouncementViewModel) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val announcements = viewModel.announcements

    var selectedAnnouncement by remember { mutableStateOf<Announcement?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("MySyncPrefs", Context.MODE_PRIVATE)

    // EDIT DIALOG
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
                        label = { Text("Title") }
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editDesc,
                        onValueChange = { editDesc = it },
                        label = { Text("Description") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.updateAnnouncement(
                        selectedAnnouncement!!.id,
                        editTitle,
                        editDesc
                    )
                    showEditDialog = false
                }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9F2))
            .padding(bottom = 20.dp)
    ) {

        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFA3B18A))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 35.dp, bottom = 10.dp)
            ) {
                Text(
                    "CR Announcement Board",
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp,
                    color = colorResource(id = R.color.dark_olive)
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = colorResource(id = R.color.border)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ADD ANNOUNCEMENT
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
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
                                )
                                title = ""
                                description = ""

                                // (kept as you said: no logic removal)
                                sharedPref.edit()
                                    .putLong("post_time", System.currentTimeMillis())
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.olive)
                            )
                        ) {
                            Text("Post to Students")
                        }
                    }
                }
            }

            // ANNOUNCEMENTS LIST
            items(announcements) { item ->

                var menuExpanded by remember { mutableStateOf(false) }

                // ✅ ADDED: Proper timestamp formatting
                val formattedTime = remember(item.timestamp) {
                    SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                        .format(item.timestamp.toDate())
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(modifier = Modifier.weight(1f)) {

                            // ✅ ADDED: Timestamp display
                            Text(
                                text = formattedTime,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )

                            Text(
                                item.title,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                item.description,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Box {
                            IconButton(onClick = {
                                menuExpanded = true
                            }) {
                                Icon(Icons.Default.MoreVert, contentDescription = null)
                            }

                            DropdownMenu(
                                expanded = menuExpanded,
                                onDismissRequest = { menuExpanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Edit") },
                                    onClick = {
                                        selectedAnnouncement = item
                                        showEditDialog = true
                                        menuExpanded = false
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text("Delete", color = Color.Red) },
                                    onClick = {
                                        viewModel.deleteAnnouncement(item.id)
                                        menuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}