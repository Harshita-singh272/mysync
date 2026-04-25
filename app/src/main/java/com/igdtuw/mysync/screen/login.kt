package com.igdtuw.mysync.screen
import com.google.firebase.firestore.FirebaseFirestore
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
            value = email, // or password
            onValueChange = { email = it },
            placeholder = { Text(" Enter University Email ID") },
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            singleLine = true, // Prevents vertical stretching
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(0.8f) // Uses 80% of screen width, safer than 250.dp
                .shadow(4.dp, RoundedCornerShape(24.dp))
                .background(Color.White, RoundedCornerShape(24.dp)),
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
            value = password, // or password
            onValueChange = { password = it },
            placeholder = { Text("Enter Password") },
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            singleLine = true, // Prevents vertical stretching
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(0.8f) // Uses 80% of screen width, safer than 250.dp
                .shadow(4.dp, RoundedCornerShape(24.dp))
                .background(Color.White, RoundedCornerShape(24.dp)),
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
                        val db = FirebaseFirestore.getInstance()

                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Login was successful, now check the role in Firestore
                                    db.collection("users").document(email).get()
                                        .addOnSuccessListener { document ->
                                            if (document != null && document.exists()) {
                                                val dbRole = document.getString("role") // This gets "CR" or "Student"

                                                // SECURITY CHECK: Does the selected role match the DB role?
                                                // We normalize the strings (CR vs Class Representative)
                                                val isCR = selectedRole == "Class Representative" && dbRole == "CR"
                                                val isStudent = selectedRole == "Student" && dbRole == "Student"

                                                if (isCR || isStudent) {
                                                    // Role matches! Proceed to Dashboard
                                                    dashboardViewModel.setUserData(email, password)
                                                    val route = if (dbRole == "CR") "cr" else "student"

                                                    navController.navigate(route) {
                                                        popUpTo("login") { inclusive = true }
                                                    }
                                                } else {
                                                    // Role mismatch
                                                    auth.signOut() // Kick them out
                                                    Toast.makeText(context, "Unauthorized role for this ID", Toast.LENGTH_LONG).show()
                                                }
                                            } else {
                                                auth.signOut()
                                                Toast.makeText(context, "User profile not found in database", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(context, "Database Error", Toast.LENGTH_SHORT).show()
                                        }
                                } else {
                                    Toast.makeText(context, "Auth Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
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