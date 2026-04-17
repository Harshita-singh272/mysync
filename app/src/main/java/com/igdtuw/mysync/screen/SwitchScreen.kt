package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
@Preview(showSystemUi = true)
@Composable
fun SwitchScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors =
                    listOf(
                        Color(0xFf63663E),
                        Color(0xFF424D2F),
                    )
            )
        )
        .padding(5.dp)
        .border(9.dp , shape= RoundedCornerShape(25.dp) , color = colorResource(id = R.color.border))
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(
                onClick = {},
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.height(180.dp).width(250.dp)
                    .border(5.dp, shape = RoundedCornerShape(15.dp), color =  Color(0xFF424D2F)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.light_olive))


            ) {
                Text(
                    text = "AS CR",
                     fontFamily = FontFamily(Font(R.font.playfairdisplay_medium)),
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.dark_grey),
                    fontSize = 30.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {},
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.height(180.dp).width(250.dp)
                    .border(5.dp, shape = RoundedCornerShape(15.dp), color = Color(0xFF424D2F)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.light_olive))

            ) {
             Text(text = "AS Student",
                 fontFamily = FontFamily(Font(R.font.playfairdisplay_medium)),
                 fontWeight = FontWeight.SemiBold,
                 color = colorResource(id = R.color.dark_grey),
                 fontSize = 30.sp)
            }
        }
    }
}













