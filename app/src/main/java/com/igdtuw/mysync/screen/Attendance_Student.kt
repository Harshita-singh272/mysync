package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.ui.theme.*
import com.igdtuw.mysync.ui.theme.components.AppCard
import com.igdtuw.mysync.viewmodel.AttendanceViewModel

@Composable
fun StudentScreen(viewModel: AttendanceViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(16.dp)
    ) {

        Text(
            text = "Attendance Overview",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(viewModel.studentList) { student ->

                AppCard {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "${student.rollNumber} - ${student.name}",
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        val status = when (student.isPresent) {
                            true -> "Present"
                            false -> "Absent"
                            null -> "Not Marked"
                        }

                        val color = when (student.isPresent) {
                            true -> PrimaryGreen
                            false -> DangerRed
                            null -> TextSecondary
                        }

                        Text(
                            text = status,
                            color = color,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}