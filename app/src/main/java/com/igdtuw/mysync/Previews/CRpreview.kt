package com.igdtuw.mysync.Previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.ui.theme.components.AppCard
import com.igdtuw.mysync.ui.theme.components.AttendanceToggleButton
import com.igdtuw.mysync.ui.theme.components.PrimaryButton

@Preview(showBackground = true, backgroundColor = 0xFFF5F3EF)
@Composable
fun CRScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mark Attendance",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E2E2E)
        )
        Spacer(modifier = Modifier.height(16.dp))


        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AppCard(modifier = Modifier.weight(1f)) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Mathematics", color = Color(0xFF2E2E2E), modifier = Modifier.weight(1f))
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color(0xFF6B6B6B))
                }
            }
            AppCard(modifier = Modifier.weight(0.6f)) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("17 Apr", color = Color(0xFF2E2E2E), modifier = Modifier.weight(1f))
                    Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color(0xFF6B6B6B))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        val mockStudents = listOf("Aayushree", "Adya", "Harshita","Charvee")
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            mockStudents.forEachIndexed { index, name ->
                AppCard {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(name, modifier = Modifier.weight(1f), color = Color(0xFF2E2E2E))


                        AttendanceToggleButton(
                            isSelected = index == 0,
                            label = "P",
                            activeColor = Color(0xFF6F7B5E),
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        AttendanceToggleButton(
                            isSelected = index == 1,
                            label = "A",
                            activeColor = Color(0xFFD9534F),
                            onClick = {}
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton("Submit Attendance", onClick = {}, modifier = Modifier.fillMaxWidth())
    }
}