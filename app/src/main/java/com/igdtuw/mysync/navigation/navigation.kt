package com.igdtuw.mysync.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.igdtuw.mysync.screen.Dash_CR
import com.igdtuw.mysync.screen.Dash_main
import com.igdtuw.mysync.screen.Login
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.igdtuw.mysync.screen.AdminAssignmentScreen
import com.igdtuw.mysync.screen.Announcement
import com.igdtuw.mysync.screen.AssignmentScreen
import com.igdtuw.mysync.screen.CRScreen
import com.igdtuw.mysync.screen.Dash_main_Cr
import com.igdtuw.mysync.screen.PostAnnouncementScreen
import com.igdtuw.mysync.screen.SplashScreen
import com.igdtuw.mysync.screen.StudentScreen
import com.igdtuw.mysync.screen.Syllabusblock
import com.igdtuw.mysync.screen.Timetableblock
import com.igdtuw.mysync.screen.ViewAnnouncementScreen
import com.igdtuw.mysync.viewmodel.AttendanceViewModel
import com.igdtuw.mysync.viewmodel.DashboardViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dashboardViewModel: DashboardViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splashscreen"
    ) {
        composable("login") {
            Login(navController, dashboardViewModel)
        }

        composable("student") {
            Dash_main(navController, dashboardViewModel)
        }
        composable("splashscreen") {
            SplashScreen(navController)
        }
        composable("cr") {
            Dash_CR(navController, dashboardViewModel)
        }
        composable("assignment") {
            AssignmentScreen(
                subjects = emptyList(),
                onEditClick = {}
            )
        }

        composable("announcements") {
            ViewAnnouncementScreen(
                announcements = emptyList()
            )
        }
        composable(route = "attendance") {
            val vm: AttendanceViewModel = viewModel()
            StudentScreen(
                viewModel = vm
            )
        }

        composable("cr_assignment") {
            AdminAssignmentScreen(
                subjects = mutableListOf(),
                onBack = { navController.popBackStack() }
            )
        }

        composable("cr_attendance") {
            val vm: AttendanceViewModel = viewModel()
            CRScreen(viewModel = vm)
        }

        composable("cr_announcements") {
            PostAnnouncementScreen()
        }
        composable("student_cr") {
            Dash_main_Cr(navController, dashboardViewModel)
        }
    }
}





