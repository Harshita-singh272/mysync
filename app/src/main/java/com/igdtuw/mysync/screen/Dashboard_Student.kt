package com.igdtuw.mysync.screen


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R

@Preview(showSystemUi = true)
@Composable
fun Dash_main_student() {
    val user = "06901012025"
    val email = "harshitasinghixa@gmail.com"
    var showprofile by remember {
        mutableStateOf(false)
    }
    val branch = "CSE-1"
    val total = 77
    val thisweekannouncements = 4
    val lastweekannouncements = 7
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.off_white))
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.background))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 15.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.dashicon),
                            contentDescription = null,
                            modifier = Modifier.size(size = 56.dp),
                            tint = colorResource(id = R.color.olive)
                        )
                        Text(
                            text = "DashBoard",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.nunito_bold)),
                            color = colorResource(id = R.color.dark_grey),
                            fontSize = 29.sp
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 6.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = { showprofile = !showprofile }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.profile),
                                    contentDescription = "Profile",
                                    modifier = Modifier.fillMaxWidth()
                                        .size(size = 80.dp),
                                    tint = colorResource(id = R.color.olive)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier
                    .padding(start = 5.dp, end= 10.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .border(2.dp, color = colorResource(id =R.color.sage_green) , shape = RoundedCornerShape(10.dp))
                            .background(color = colorResource(id = R.color.light_sage_gray),shape= RoundedCornerShape(10.dp))
                            .padding(10.dp)


                    ) {
                        Text(
                            text = "Student Portal",
                            fontSize = 17.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {},
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(0.97f)
                        .height(155.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.off_white))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(
                                top = 10.dp,
                                start = 0.dp,
                                end = 5.dp,
                                bottom = 5.dp
                            ),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column() {
                            Row {
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "Assignment",
                                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                    fontSize = 25.sp,
                                    color = colorResource(id = R.color.dark_grey),
                                )
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.assignment),
                                        contentDescription = null,
                                        modifier = Modifier.size(size = 40.dp),
                                        tint = colorResource(id = R.color.olive)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(
                                text = "(All the subject-wise assignments)",
                                fontSize = 17.sp,
                                color = colorResource(id = R.color.grey),
                                fontFamily = FontFamily(Font(R.font.nunito_light)),
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {},
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(0.97f)
                        .height(155.dp)
                        .padding(
                            top = 5.dp,
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 5.dp
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.off_white))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .padding(
                                    top = 10.dp,
                                    start = 0.dp,
                                    end = 0.dp,
                                    bottom = 0.dp
                                ),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Row {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Attendance",
                                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                    fontSize = 25.sp,
                                    color = colorResource(id = R.color.dark_grey),
                                )
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.speaker),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.olive),
                                        modifier = Modifier.size(size = 47.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row {
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = "Branch :",
                                    color = colorResource(id = R.color.grey),
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = branch,
                                    color = colorResource(id = R.color.grey),
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.nunito_light)),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row {
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = "Total Strength :",
                                    color = colorResource(id = R.color.grey),
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "$total",
                                    color = colorResource(id = R.color.grey),
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.nunito_light)),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {},
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(0.97f)
                        .height(155.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.off_white))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(
                                top = 10.dp,
                                start = 0.dp,
                                end = 5.dp,
                                bottom = 5.dp
                            ),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column {
                            Row {
                                Spacer(modifier = Modifier.height(30.dp))
                                Text(
                                    text = "Announcements",
                                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                                    fontSize = 25.sp,
                                    color = colorResource(id = R.color.dark_grey),
                                )
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.bell),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.olive),
                                        modifier = Modifier.size(size = 40.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row {
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "This Week :",
                                        color = colorResource(id = R.color.grey),
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.nunito_light)),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(17.dp))
                                    Text(
                                        text = "$thisweekannouncements announcements",
                                        color = colorResource(id = R.color.grey),
                                        fontSize = 17.sp,
                                        fontFamily = FontFamily(Font(R.font.nunito_light)),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Row {
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "Last Week :",
                                        color = colorResource(id = R.color.grey),
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.nunito_light)),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(17.dp))
                                    Text(
                                        text = "$lastweekannouncements announcements",
                                        color = colorResource(id = R.color.grey),
                                        fontSize = 17.sp,
                                        fontFamily = FontFamily(Font(R.font.nunito_light)),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
                Syllabusblock()
                Timetableblock()
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
        if (showprofile) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showprofile = false }, contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 60.dp, end = 10.dp) // Offset from the very corner
                        .fillMaxWidth(0.70f) // Takes 70% of screen width
                        .wrapContentHeight() // Only as tall as its content
                        .border(
                            3.dp,
                            color = colorResource(id = R.color.sage_green),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(
                            shape = RoundedCornerShape(20.dp),
                            color = colorResource(id = R.color.button)
                        )
                        .padding(15.dp)
                        .clickable(enabled = false) { } // Prevents closing when clicking inside the card
                )
                {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.big_profile),
                            contentDescription = "My Profile",
                            modifier = Modifier.size(120.dp)
                                .border(
                                    6.dp,
                                    color = colorResource(id = R.color.sage_gray),
                                    shape = RoundedCornerShape(80.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Row {
                            Text(
                                text = "Username:  ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = user,
                                fontSize = 15.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text(
                                text = "Email:  ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = email,
                                fontSize = 15.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        Button(
                            onClick = {
                                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.olive))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.logout),
                                    contentDescription = "LogOut",
                                    modifier = Modifier.size(30.dp),
                                    tint = colorResource(id = R.color.light_olive)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "LogOut",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
