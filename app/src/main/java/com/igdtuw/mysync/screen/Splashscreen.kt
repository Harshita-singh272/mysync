package com.igdtuw.mysync.screen

import android.content.Context
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.igdtuw.mysync.R

import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun SplashScreen(navController: NavController) {

//    LaunchedEffect(Unit) {
//        delay(1000) // 3 seconds delay
//
//        navController.navigate("login") {
//            popUpTo("splash") { inclusive = true}
//        }
//    }
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    LaunchedEffect(Unit) {
        delay(1000)

        val isLoggedIn = prefs.getBoolean("is_logged_in", false)
        val role = prefs.getString("role", "Student")

        if (isLoggedIn) {
            if (role == "Student") {
                navController.navigate("student") {
                    popUpTo("splashscreen") { inclusive = true }
                }
            } else {
                navController.navigate("cr") {
                    popUpTo("splashscreen") { inclusive = true }
                }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splashscreen") { inclusive = true }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()
        .background(color = colorResource(id = R.color.light_olive))){
        Image(
            painter = painterResource( id = R.drawable.logo),
            contentDescription =null,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
        )
    }
}