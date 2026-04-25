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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.viewmodel.AttendanceViewModel

@Composable
fun StudentScreen(viewModel: AttendanceViewModel, studentName: String) {
    LaunchedEffect(studentName) {
        viewModel.fetchStudentAttendance(studentName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(20.dp)
    ) {
        Text(
            text = "Your Attendance",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF2D2D2D)
        )
        Text(
            text = "Viewing records for $studentName",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (viewModel.subjectAttendance.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("No attendance history found.", color = Color.LightGray)
                    Text("Check back once the CR uploads data.", fontSize = 12.sp, color = Color.LightGray)
                }
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
                                    color = if (item.percentage < 75) Color(0xFFD32F2F) else Color(0xFF388E3C)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Smooth Progress Bar
                            LinearProgressIndicator(
                                progress = item.percentage / 100f,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .clip(RoundedCornerShape(5.dp)),
                                color = if (item.percentage < 75) Color(0xFFD32F2F) else Color(0xFF388E3C),
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