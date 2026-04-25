package com.igdtuw.mysync.screen
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.DashboardViewModel

@Composable
fun Login(navController: NavController, dashboardViewModel: DashboardViewModel) {
    val context = LocalContext.current


    var expanded by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("Role") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_olive)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            color = colorResource(id = R.color.olive),
            modifier = Modifier.padding(top = 80.dp, bottom = 40.dp),
        )

        // Role Dropdown
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
                modifier = Modifier.align(Alignment.Center),
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

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("University Email ID") },
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = R.color.sage_green),
                focusedBorderColor = colorResource(id = R.color.olive),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text("Password") },
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = R.color.sage_green),
                focusedBorderColor = colorResource(id = R.color.olive),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Login Button
        Button(
            // Inside your Button in login.kt
            onClick = {
                val auth = FirebaseAuth.getInstance()

                when {
                    selectedRole == "Role" -> {
                        Toast.makeText(context, "Please select a role", Toast.LENGTH_SHORT).show()
                    }
                    email.isBlank() -> {
                        Toast.makeText(context, "Enter your Email", Toast.LENGTH_SHORT).show()
                    }
                    // SAFETY CHECK: Only allows IGDTUW IDs
                    !email.endsWith("@igdtuw.ac.in") -> {
                        Toast.makeText(context, "Use your @igdtuw.ac.in ID", Toast.LENGTH_SHORT).show()
                    }
                    password.isBlank() -> {
                        Toast.makeText(context, "Enter your Password", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // This is the Firebase magic
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // 1. Save login state locally
                                    val prefs = context.getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
                                    prefs.edit()
                                        .putBoolean("is_logged_in", true)
                                        .putString("role", selectedRole)
                                        .apply()

                                    // 2. Update the Dashboard data
                                    dashboardViewModel.setUserData(email, password)

                                    // 3. Move to the correct Dashboard
                                    if (selectedRole == "Student") {
                                        navController.navigate("student") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    } else {
                                        navController.navigate("cr") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    }
                                } else {
                                    // If password is wrong or user isn't in your Firebase List
                                    Toast.makeText(context, "Login Failed: Check email or password", Toast.LENGTH_LONG).show()
                                }
                            }
                    }
                }
            },
            modifier = Modifier.width(250.dp).shadow(4.dp, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.sage_green))
        ) {
            Text("Login", color = colorResource(id = R.color.cream), fontWeight = FontWeight.Bold)
        }
    }
}