package com.igdtuw.mysync.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igdtuw.mysync.screen.Dash_CR
import com.igdtuw.mysync.screen.Dash_main

@Composable
fun MySync_NavigationSystem(){
    val navController = rememberNavController()
    NavHost (startDestination = Routes.Dash_main.route, navController = navController){
        composable(Routes.Dash_main.route){
            Dash_main()
        }

        composable(Routes.Dash_CR.route){
            Dash_CR()
        }
    }
}

