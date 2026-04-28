//package com.igdtuw.mysync.screen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.igdtuw.mysync.R
//import com.igdtuw.mysync.viewmodel.AnnouncementViewModel
//
//@Composable
//fun Announcement_Student(viewModel: AnnouncementViewModel) {
//    val announcements = viewModel.announcements
//
//    Column(modifier = Modifier.fillMaxSize()
//        .background(Color(0xFFF7F9F2))
//    ) {
//        Box(modifier = Modifier.fillMaxWidth()
//            .background(
//                color = Color(0xFFA3B18A )),
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp, top = 35.dp, bottom = 10.dp)
//            ) {
//                Text(
//                    "Announcements",
//                    fontWeight = FontWeight.Black,
//                    fontSize = 32.sp,
//                    color = Color(0xFF424D2F)
//
//
//                )
//            }
//        }
//        HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.border))
//        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//            items(announcements) { item ->
////                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
////                    Column(modifier = Modifier.padding(16.dp)) {
////                        Text(item.title, fontWeight = FontWeight.Bold, color = colorResource(id = R.color.sage_green))
////                        Spacer(Modifier.height(4.dp))
////                        Text(item.description, fontSize = 14.sp)
////                    }
////                }
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = CardDefaults.cardColors(containerColor = Color.White),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(
//                            item.title,
//                            fontWeight = FontWeight.SemiBold,
//                            fontSize = 20.sp,
//                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
//                            color = colorResource(id = R.color.dark_grey)
//                        )
//                        Spacer(Modifier.height(4.dp))
//                        Text(item.description, fontSize = 14.sp)
//                    }
//                }
//            }
//        }
//    }
//}
package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.AnnouncementViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Announcement_Student(viewModel: AnnouncementViewModel) {

    val announcements = viewModel.announcements

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9F2))
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
                    .padding(start = 20.dp, top = 35.dp, bottom = 10.dp)
            ) {
                Text(
                    "Announcements",
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    color = Color(0xFF424D2F)
                )
            }
        }

        HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.border))

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(announcements) { item ->

                // ✅ FORMAT TIMESTAMP
                val formattedTime = remember(item.timestamp) {
                    SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                        .format(item.timestamp.toDate())
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        // 🕒 TIMESTAMP
                        Text(
                            text = formattedTime,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(Modifier.height(4.dp))

                        // TITLE
                        Text(
                            item.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            color = colorResource(id = R.color.dark_grey)
                        )

                        Spacer(Modifier.height(4.dp))

                        // DESCRIPTION
                        Text(
                            item.description,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}