package com.igdtuw.mysync.Previews

import com.igdtuw.mysync.ui.theme.components.AppCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.AppCard


@Preview(showBackground = true, backgroundColor = 0xFFF5F3EF)
@Composable
fun StudentScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Attendance Report",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E2E2E)
        )
        Text("Semester 4 • Batch 2026", color = Color(0xFF6B6B6B), fontSize = 14.sp)

        Spacer(modifier = Modifier.height(24.dp))


        AppCard {
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Text("Mathematics", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("90%", color = Color(0xFF6F7B5E), fontWeight = FontWeight.Bold)
                }
                Text("Attended: 18/20", color = Color(0xFF6B6B6B), fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.9f,
                    modifier = Modifier.fillMaxWidth().height(6.dp),
                    color = Color(0xFF6F7B5E),
                    trackColor = Color(0xFFDCE3D2),
                    strokeCap = StrokeCap.Round
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        AppCard {
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Text("Data Structures", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("60%", color = Color(0xFFD9534F), fontWeight = FontWeight.Bold)
                }
                Text("Attended: 12/20", color = Color(0xFF6B6B6B), fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.6f,
                    modifier = Modifier.fillMaxWidth().height(6.dp),
                    color = Color(0xFFD9534F),
                    trackColor = Color(0xFFDCE3D2),
                    strokeCap = StrokeCap.Round
                )
            }
        }
    }
}