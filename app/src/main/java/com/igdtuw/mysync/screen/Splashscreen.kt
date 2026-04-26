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
import com.igdtuw.mysync.viewmodel.DashboardViewModel
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(navController: NavController, dashboardViewModel: DashboardViewModel) {
    val context = LocalContext.current
    val alphaAnim = remember { Animatable(0f) }

    LaunchedEffect(true) {
        alphaAnim.animateTo(1f, tween(1500))
        delay(1000)

        val sharedPref = context.getSharedPreferences("MySyncPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPref.getString("user_email", null)
        val savedRole = sharedPref.getString("user_role", null)

        if (!savedEmail.isNullOrEmpty() && !savedRole.isNullOrEmpty()) {

            dashboardViewModel.setUserData(savedEmail, "auto_login")

            navController.navigate(savedRole.lowercase()) {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }
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
                .alpha(alphaAnim.value)
        )
    }
}