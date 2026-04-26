package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.AnnouncementViewModel

@Composable
fun Announcement_Student(viewModel: AnnouncementViewModel) {
    val announcements = viewModel.announcements

    Column(modifier = Modifier.fillMaxSize().background(colorResource(id = R.color.cream))) {
        Box(
            modifier = Modifier.fillMaxWidth().background(colorResource(id = R.color.sage_green)).padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Announcements", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(announcements) { item ->
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(item.title, fontWeight = FontWeight.Bold, color = colorResource(id = R.color.sage_green))
                        Spacer(Modifier.height(4.dp))
                        Text(item.description, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}