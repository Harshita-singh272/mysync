package com.igdtuw.mysync.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.R

data class Announcement(val title:String, val des:String)
class AnnouncementViewModel : ViewModel() {

    private val _announcements = mutableStateListOf<Announcement>()
    val announcements: List<Announcement> = _announcements

    fun addAnnouncement(title: String, description: String) {
        _announcements.add(Announcement(title, description))
    }

    fun deleteAnnouncement(announcement: Announcement) {
        _announcements.remove(announcement)
    }

    fun updateAnnouncement(old: Announcement, newTitle: String, newDesc: String) {
        val index = _announcements.indexOf(old)
        if (index != -1) {
            _announcements[index] = Announcement(newTitle, newDesc)
        }
    }
}
@Composable
fun ViewAnnouncementScreen(announcements: List<Announcement>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id=R.color.cream))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id=R.color.sage_green))
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Announcements",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        if (announcements.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No announcements yet",
                    color = colorResource(id=R.color.sage_green),
                    fontSize = 15.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(announcements) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                item.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = colorResource(id = R.color.sage_green)
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                item.des,
                                fontSize = 14.sp,
                                color = colorResource(id = R.color.sage_green)
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewViewScreen() {

    val sampleList = listOf(
        Announcement("Announcement 1", "Team meeting at 5 PM"),
        Announcement("Announcement 2", "Hackathon tomorrow"),
        Announcement("Announcement 3", "Submit assignment")
    )

    ViewAnnouncementScreen(sampleList)
}