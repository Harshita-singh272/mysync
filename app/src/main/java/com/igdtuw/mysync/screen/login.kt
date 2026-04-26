package com.igdtuw.mysync.screen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.igdtuw.mysync.R
import com.igdtuw.mysync.viewmodel.DashboardViewModel

@Composable
fun Login(navController: NavController, dashboardViewModel: DashboardViewModel) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    var isLoading by remember { mutableStateOf(false) }

    // Your Web Client ID from Firebase Console
    val webClientId = "641437759486-q5iq3vpkqnv7cbi3bsk6p7k0sc172c71.apps.googleusercontent.com"

    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                // Step 1: Firebase Authentication
                auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        val email = account.email?.lowercase() ?: ""

                        // Speed Boost: Immediate feedback
                        Toast.makeText(context, "Verifying Student Access...", Toast.LENGTH_SHORT).show()

                        // Step 2: Firestore Whitelist Check
                        db.collection("users").document(email).get(Source.DEFAULT)
                            .addOnSuccessListener { doc ->
                                if (doc.exists()) {
                                    val role = doc.getString("role")?.lowercase() ?: "student"
                                    dashboardViewModel.setUserData(email, "google_auth")

                                    // Step 3: Success Navigation
                                    navController.navigate(role) {
                                        popUpTo("login") { inclusive = true }
                                    }
                                } else {
                                    // Fail: User not in Firestore
                                    auth.signOut()
                                    googleSignInClient.signOut()
                                    isLoading = false
                                    Toast.makeText(context, "Access Denied: CSE '29 Whitelist Only", Toast.LENGTH_LONG).show()
                                }
                            }
                            .addOnFailureListener {
                                isLoading = false
                                Toast.makeText(context, "Database Error. Check connection.", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        isLoading = false
                        googleSignInClient.signOut()
                        Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: ApiException) {
                isLoading = false
                googleSignInClient.signOut()
                Toast.makeText(context, "Login Cancelled", Toast.LENGTH_SHORT).show()
            }
        } else {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE8EDDF), // Sophisticated Light Sage
                        Color(0xFFF5F5F5)  // Pearl White
                    )
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
            // App Branding Section
            Text(
                text = "My ClassSync",
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4F5D3D), // Deep Professional Olive
                letterSpacing = (-1.5).sp
            )

            Text(
                text = "Elevating CSE '29",
                fontSize = 16.sp,
                color = Color(0xFF707A65),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    color = Color(0xFF4F5D3D),
                    strokeWidth = 3.dp
                )
            } else {
                // Sleek Floating Card for Button
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    shadowElevation = 8.dp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Welcome Back",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2D2D2D)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Use your University email to continue",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        // Modern Login Button
                        Button(
                            onClick = {
                                isLoading = true
                                launcher.launch(googleSignInClient.signInIntent)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4F5D3D)
                            )
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.google),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Continue with Google",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Footer
            Text(
                text = "Built for the Batch, by the Batch",
                fontSize = 12.sp,
                color = Color.Gray.copy(alpha = 0.6f),
                fontWeight = FontWeight.Light
            )
        }
    }
}