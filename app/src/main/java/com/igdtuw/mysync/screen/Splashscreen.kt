package com.igdtuw.mysync.screen

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
import com.igdtuw.mysync.R

@Preview(showSystemUi = true)
@Composable
fun SplashScreen (){
    Box(modifier = Modifier.fillMaxSize()
        .background(color = colorResource(id = R.color.background))){
        Image(
            painter = painterResource( id = R.drawable.logo),
            contentDescription =null,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.Center)
        )
    }
}