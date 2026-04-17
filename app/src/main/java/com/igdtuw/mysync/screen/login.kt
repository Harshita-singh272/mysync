package com.igdtuw.mysync.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.igdtuw.mysync.R
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
@Preview(showSystemUi = true)
fun Login() {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedRole by remember {
        mutableStateOf("Role")
    }
    var enrollmentNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.cream)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Login",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            color = colorResource(id = R.color.olive),
            modifier = Modifier.padding(top = 80.dp, bottom = 40.dp),
        )
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp))
                .background(Color.White, RoundedCornerShape(24.dp))
                .border(1.5.dp, colorResource(id = R.color.sage_green), RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp)
                .clickable { expanded = true })
        {
            Text(
                text = selectedRole,
                color = colorResource(id = R.color.olive),
                fontWeight = FontWeight.Bold,
                modifier=Modifier.align(Alignment.Center),
                fontSize = 18.sp,
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = colorResource(id = R.color.sage_green),
                modifier = Modifier.align(Alignment.CenterEnd),
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                listOf("Student", "Class Representative").forEach { role ->
                    DropdownMenuItem(
                        text = { Text(text = role) },
                        onClick = {
                            selectedRole = role
                            expanded = false
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = enrollmentNumber,
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            onValueChange = { enrollmentNumber = it },
            placeholder = { Text("Enrolment Number") },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(24.dp))
                .background(Color.White, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = R.color.sage_green),
                focusedBorderColor = colorResource(id = R.color.olive),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text("Password") },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(24.dp))
                .background(Color.White, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = R.color.sage_green),
                focusedBorderColor = colorResource(id = R.color.olive),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .width(250.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.sage_green))
        ) {
            Text("Login", color = colorResource(id=R.color.cream), fontWeight = FontWeight.Bold)
        }
    }
}