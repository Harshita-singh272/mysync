package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.ui.theme.*
import com.igdtuw.mysync.ui.theme.components.AppCard
import com.igdtuw.mysync.ui.theme.components.AttendanceToggleButton
import com.igdtuw.mysync.viewmodel.AttendanceViewModel

@Composable
fun CRScreen(viewModel: AttendanceViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(16.dp)
    ) {

        Text(
            text = "Mark Attendance",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔽 SUBJECT + DATE (TOP BAR)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = viewModel.selectedSubject,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray)
                    .padding(10.dp)
                    .clickable {
                        viewModel.selectedSubject =
                            if (viewModel.selectedSubject == "Mathematics") "DSA" else "Mathematics"

                        viewModel.loadAttendance()
                    }
            )

            Text(
                text = viewModel.selectedDate,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray)
                    .padding(10.dp)
                    .clickable {
                        viewModel.selectedDate =
                            if (viewModel.selectedDate == "23/04/2026") "24/04/2026" else "23/04/2026"

                        viewModel.loadAttendance()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔽 STUDENT LIST
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {

            items(viewModel.studentList) { student ->

                AppCard {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "${student.rollNumber} - ${student.name}",
                            modifier = Modifier.weight(1f),
                            color = TextPrimary
                        )

                        AttendanceToggleButton(
                            isSelected = student.isPresent == true,
                            label = "P",
                            activeColor = PrimaryGreen,
                            onClick = {
                                viewModel.updateAttendance(student.id, true)
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        AttendanceToggleButton(
                            isSelected = student.isPresent == false,
                            label = "A",
                            activeColor = DangerRed,
                            onClick = {
                                viewModel.updateAttendance(student.id, false)
                            }
                        )
                    }
                }
            }
        }
    }
}