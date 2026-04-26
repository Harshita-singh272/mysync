package com.igdtuw.mysync.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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

    LazyColumn(modifier = Modifier.fillMaxSize().background(cream)) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.background))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 25.dp,
                        start =10.dp,
                        bottom =10.dp)
                ) {
                    Text(
                        "ASSIGNMENT",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        color = colorResource(id = R.color.dark_grey),
                        fontSize = 29.sp
                    )
                }
            }
        }

        items(subjects) { subject ->

            var expanded by remember { mutableStateOf(false) }

            val theoryList = assignments.filter {
                it.subject == subject && it.type == "theory"
            }

            val labList = assignments.filter {
                it.subject == subject && it.type == "lab"
            }

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .border(
                        width = if (expanded) 3.dp else 0.dp,
                        color = colorResource(id = R.color.sage_green),
                        shape = RoundedCornerShape(12.dp)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = olive
                ),
                ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row() {
                        Text(
                            subject, color = cream,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                fontWeight = FontWeight.Bold,
                                fontSize= 15.sp)
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "Theory: ${theoryList.size}       Lab: ${labList.size}",
                                color = colorResource(id = R.color.dark_grey),
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.nunito_light))
                            )
                        }
                    }
                    if (expanded) {
                        Column(modifier = Modifier.padding(vertical = 6.dp)) {
                            Text("Theory", color =colorResource(id = R.color.sage_gray),
                                fontFamily = FontFamily(Font(R.font.merriweather_24pt_bold)),
                                fontSize = 15.sp)
                            if (theoryList.isNotEmpty()) {
                                theoryList.forEach {

                                    Column() {
                                        AssignmentItemUI(it, context)

                                        Spacer(modifier = Modifier.height(5.dp))
                                        HorizontalDivider(
                                            thickness = 1.dp,
                                            color = colorResource(id = R.color.light_sage_gray)
                                        )
                                    }
                                }

                            } else {
                                Text(
                                    "No Theory Assignment",
                                    color = colorResource(id = R.color.sage_green)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("Lab",color =colorResource(id = R.color.sage_gray),
                                fontFamily = FontFamily(Font(R.font.merriweather_24pt_bold)),
                                fontSize = 15.sp)
                            if (labList.isNotEmpty()) {
                                labList.forEach {

                                    Column() {
                                        AssignmentItemUI(it, context)

                                        Spacer(modifier = Modifier.height(5.dp))
                                        HorizontalDivider(
                                            thickness = 1.dp,
                                            color = colorResource(id = R.color.light_sage_gray)
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    "No Lab Assignment",
                                    color = colorResource(id = R.color.sage_green)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun AssignmentItemUI(item: AssignmentItem, context: Context) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(
            text = item.title,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.clickable {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                )
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (item.description.isNotEmpty()) {
            Text(
                text = item.description,
                color = Color.LightGray,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}