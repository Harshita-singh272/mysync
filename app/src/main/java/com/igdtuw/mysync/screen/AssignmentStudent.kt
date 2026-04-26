package com.igdtuw.mysync.screen

import android.R.color.white
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.InsertLink
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import com.igdtuw.mysync.model.AssignmentItem
import com.igdtuw.mysync.viewmodel.AssignmentViewModel
import com.igdtuw.mysync.ui.theme.*

@Composable
fun AssignmentScreen(
    viewModel: AssignmentViewModel,
    onEditClick: () -> Unit
) {
    val assignments by viewModel.assignments.collectAsState()
    val subjects = viewModel.subjects
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(cream)
    ) {
        item {
            Box(modifier = Modifier.fillMaxWidth().background(

            color = Color(0xFFA3B18A )),
                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 35.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = "Assignments",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color =colorResource(id= R.color.dark_olive)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Track your CSE '29 submissions",
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.dark_grey)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

        }

        items(subjects) { subject ->
            var expanded by remember { mutableStateOf(false) }
            val theoryList = assignments.filter { it.subject == subject && it.type == "theory" }
            val labList = assignments.filter { it.subject == subject && it.type == "lab" }

            if (theoryList.isNotEmpty() || labList.isNotEmpty()) {
                Surface(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = if (expanded) olive else Color.White,
                    shadowElevation = if (expanded) 8.dp else 2.dp,
                    onClick = { expanded = !expanded }
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = subject,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (expanded) Color.White else Color.DarkGray
                            )
                            Badge(
                                containerColor = if (expanded) cream else olive.copy(alpha = 0.1f),
                                contentColor = olive
                            ) {
                                Text(
                                    text = "${theoryList.size + labList.size}",
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }

                        AnimatedVisibility(
                            visible = expanded,
                            enter = expandVertically(animationSpec = tween(400)),
                            exit = shrinkVertically()
                        ) {
                            Column(modifier = Modifier.padding(top = 16.dp)) {
                                if (theoryList.isNotEmpty()) {
                                    SubHeader("Theory", Color.White.copy(alpha = 0.7f))
                                    theoryList.forEach { StudentAssignmentItem(it, context) }
                                }

                                if (labList.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    SubHeader("Lab / Practical", Color.White.copy(alpha = 0.7f))
                                    labList.forEach { StudentAssignmentItem(it, context) }
                                }
                            }
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}

@Composable
fun SubHeader(title: String, color: Color) {
    Text(
        text = title.uppercase(),
        fontSize = 10.sp,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = 1.2.sp,
        color = color,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun StudentAssignmentItem(item: AssignmentItem, context: Context) {
    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable {
                if (item.link.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                        context.startActivity(intent)
                    } catch (e: Exception) {
                    }
                }
            },
        color = Color.White.copy(alpha = 0.15f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.InsertLink,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = item.title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
                if (item.description.isNotEmpty()) {
                    Text(
                        text = item.description,
                        color = cream.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}