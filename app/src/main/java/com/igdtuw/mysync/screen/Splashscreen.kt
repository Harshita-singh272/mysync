package com.igdtuw.mysync.screen

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.igdtuw.mysync.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current

    // 1. Properly initialize the alpha animation state
    val alphaAnim = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        alphaAnim.animateTo(1f, animationSpec = tween(1500))
        delay(1000)

        val sharedPref = context.getSharedPreferences("MySyncPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPref.getString("user_email", null)
        val savedRole = sharedPref.getString("user_role", null)

        // ONLY go to dashboard if BOTH email and role exist
        if (!savedEmail.isNullOrEmpty() && !savedRole.isNullOrEmpty()) {
            navController.navigate(savedRole.lowercase()) {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            // Otherwise, ALWAYS force the login screen
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }
    // 3. Keep the UI inside the function (removed the extra closing bracket)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.light_olive))
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
                .alpha(alphaAnim.value) // 4. Apply the animation to the logo
        )
    }
}