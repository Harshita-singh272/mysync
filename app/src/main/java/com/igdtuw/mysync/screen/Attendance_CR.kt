package com.igdtuw.mysync.screen


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.model.Student
import com.igdtuw.mysync.ui.theme.BackgroundCream
import com.igdtuw.mysync.ui.theme.DangerRed
import com.igdtuw.mysync.ui.theme.PrimaryGreen
import com.igdtuw.mysync.ui.theme.TextPrimary
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
                            text = student.name,
                            modifier = Modifier.weight(1f),
                            color = TextPrimary
                        )

                        AttendanceToggleButton(
                            isSelected = student.isPresent == true,
                            label = "P",
                            activeColor = PrimaryGreen,
                            onClick = { viewModel.updateAttendance(student.id, true) }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        AttendanceToggleButton(
                            isSelected = student.isPresent == false,
                            label = "A",
                            activeColor = DangerRed,
                            onClick = { viewModel.updateAttendance(student.id, false) }
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CRScreenPreview() {

    val fakeViewModel = AttendanceViewModel().apply {
        studentList = listOf(
            Student(1, "Aanya Sharma", true),
            Student(2, "Rohan Mehta", false),
            Student(3, "Priya Kapoor", null)
        )
    }

    CRScreen(viewModel = fakeViewModel)
}