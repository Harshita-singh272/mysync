package com.igdtuw.mysync.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.igdtuw.mysync.viewmodel.DashboardViewModel

@Composable
fun Login(navController: NavController, dashboardViewModel: DashboardViewModel) {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    var emailInput by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE8EDDF), Color(0xFFF5F5F5))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "ClassSync",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4F5D3D),
                letterSpacing = (-1.5).sp
            )

            Text(
                text = "CSE '29",
                fontSize = 18.sp,
                color = Color(0xFF707A65),
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(60.dp))

            Surface(
                shape = RoundedCornerShape(28.dp),
                shadowElevation = 12.dp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(28.dp)) {
                    Text(
                        text = "Student Login",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2D2D2D)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = emailInput,
                        onValueChange = { emailInput = it.lowercase().trim() },
                        label = { Text("College Email ID") },
                        placeholder = { Text("yourname@igdtuw.ac.in") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF4F5D3D),
                            focusedLabelColor = Color(0xFF4F5D3D),
                            cursorColor = Color(0xFF4F5D3D)
                        )
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = {
                            if (emailInput.contains("@")) {
                                isLoading = true
                                db.collection("users").document(emailInput).get()
                                    .addOnSuccessListener { doc ->
                                        isLoading = false
                                        if (doc.exists()) {
                                            val role = doc.getString("role")?.lowercase() ?: "student"

                                            // Save Session
                                            val sharedPref = context.getSharedPreferences("MySyncPrefs", Context.MODE_PRIVATE)

                                            sharedPref.edit()
                                                .putString("user_email", emailInput)
                                                .putString("user_role", role)
                                                .putLong("login_time", System.currentTimeMillis())
                                                .apply()

                                            dashboardViewModel.setUserData(emailInput, "direct_auth")
                                            navController.navigate(role) {
                                                popUpTo("login") { inclusive = true }
                                            }
                                        } else {
                                            Toast.makeText(context, "Access Denied: Not in CSE '29 list", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                    .addOnFailureListener {
                                        isLoading = false
                                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                Toast.makeText(context, "Enter a valid college email", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        shape = RoundedCornerShape(16.dp),
                        enabled = !isLoading,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F5D3D))
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                        } else {
                            Text("Verify & Enter", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(
                modifier = Modifier.width(40.dp).padding(bottom = 12.dp),
                thickness = 2.dp,
                color = Color(0xFF4F5D3D).copy(alpha = 0.3f)
            )
            Text(
                text = "BY THE BATCH, FOR THE BATCH",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4F5D3D).copy(alpha = 0.6f),
                letterSpacing = 2.sp, // Adds that high-end tech vibe
                textAlign = TextAlign.Center
            )
            Text(
                text = "© 2026 IGDTUW CSE",
                fontSize = 10.sp,
                color = Color.Gray.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}