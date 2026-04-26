package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.AttendanceViewModel

@Composable
fun StudentScreen(viewModel: AttendanceViewModel, studentName: String, studentEmail: String) {

    LaunchedEffect(studentEmail) {
        if (studentEmail.isNotEmpty()) {
            viewModel.fetchStudentAttendance(studentEmail)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF7F9F2)).padding(bottom= 20.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()
            .background(
                color = Color(0xFFA3B18A )),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 35.dp, bottom = 10.dp)
            ) {
                Text(
                    text = "Your Attendance",
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    color = colorResource(id= R.color.dark_olive)
                )
                Text(
                    text = "Viewing records for $studentName",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.dark_grey)
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.border))
        Spacer(modifier = Modifier.height(24.dp))
        Column(modifier= Modifier.padding(start= 20.dp , end = 20.dp)) {
            if (studentEmail.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: User Email not found. Please re-login.", color = Color.Red)
                }
            } else if (viewModel.subjectAttendance.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    // This is where the spinner happens
                    CircularProgressIndicator(color = Color(0xFF6B705C))
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(viewModel.subjectAttendance) { item ->
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            shadowElevation = 3.dp,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        item.name,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF333333)
                                    )
                                    Text(
                                        "${item.percentage.toInt()}%",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = if (item.percentage < 75) Color(0xFFD32F2F) else Color(
                                            0xFF388E3C
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                LinearProgressIndicator(
                                    progress = item.percentage / 100f,
                                    modifier = Modifier.fillMaxWidth().height(10.dp)
                                        .clip(RoundedCornerShape(5.dp)),
                                    color = if (item.percentage < 75) Color(0xFFD32F2F) else Color(
                                        0xFF388E3C
                                    ),
                                    trackColor = Color(0xFFE0E0E0)
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "${item.attended} classes attended out of ${item.total}",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}