package com.igdtuw.mysync.screen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.igdtuw.mysync.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Preview(showSystemUi = true)
@Composable
fun Syllabusblock() {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(10.dp))
    Column(modifier = Modifier.padding(start = 5.dp,
        end= 5.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth(0.97f)
                .wrapContentHeight()
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(15.dp),
                    clip = false
                )
                .clip(RoundedCornerShape(15.dp))
                .background(color = colorResource(id = R.color.off_white))
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 0.dp,
                    start = 0.dp,
                    end = 25.dp,
                    bottom = 10.dp
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        top = 27.dp,
                        start = 30.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
                ) {
                    Text(
                        text = "Syllabus",
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        fontSize = 25.sp,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.syllabus),
                            contentDescription = null,
                            modifier = Modifier.size(size = 40.dp),
                            tint = colorResource(id = R.color.olive)
                        )
                    }
                }
                Syllabusdp()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {},
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            colorResource(id = R.color.olive)
                        )
                    ) {
                        Text("Open")
                    }
                }
            }
        }
    }
}
@Composable
fun Syllabusdp(){
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectsubject by remember {
        mutableStateOf("Please choose your subject")
    }
    Box(
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth()
            .border(2.dp, color = colorResource(id =R.color.sage_green), shape = RoundedCornerShape(10.dp))
    ){
        Text(
            text = selectsubject,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 16.sp,
            color = colorResource(id= R.color.dark_grey)
        )
        IconButton(onClick = {expanded = !expanded }) {
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Subjects",
                modifier = Modifier
                    .padding(10.dp),
                tint = colorResource(id = R.color.olive)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Mobile Application Development") },
                onClick = { /* Do something... */
                    expanded = false
                    selectsubject = "Mobile Application Development"
                }
            )
            DropdownMenuItem(
                text = { Text("Probability and Statistics") },
                onClick = {
                    expanded = false
                    selectsubject = "Probability and Statistics"
                }
            )
            DropdownMenuItem(
                text = { Text("Environment science") },
                onClick = { /* Do something... */
                    expanded = false
                    selectsubject = "Environment science"
                }
            )
            DropdownMenuItem(
                text = { Text("Soft skills and personality development") },
                onClick = { /* Do something... */
                    expanded = false
                    selectsubject = "Soft skills and personality development"
                }
            )
            DropdownMenuItem(
                text = { Text("Introduction to data science") },
                onClick = { /* Do something... */
                    expanded = false
                    selectsubject = "Introduction to data science"
                }
            )
            DropdownMenuItem(
                text = { Text("Data Structure") },
                onClick = { /* Do something... */
                    expanded = false
                    selectsubject = "Data Structure"
                }
            )
        }
    }

}