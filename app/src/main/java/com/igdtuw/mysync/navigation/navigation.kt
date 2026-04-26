package com.igdtuw.mysync.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igdtuw.mysync.screen.* import com.igdtuw.mysync.viewmodel.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dashboardViewModel: DashboardViewModel = viewModel()
    val announcementViewModel: AnnouncementViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splashscreen"
    ) {
        composable("splashscreen") { SplashScreen(navController) }
        composable("login") { Login(navController, dashboardViewModel) }
        composable("student") { Dash_main(navController, dashboardViewModel) }
        composable("cr") { Dash_CR(navController, dashboardViewModel) }

        // Announcements for Student
        composable("announcements") {
            Announcement_Student(announcementViewModel)
        }

        // Announcements for CR (Combined Post + Manage)
        composable("cr_announcements") {
            Announcement_CR(announcementViewModel)
        }

        // Attendance & Assignments (Keeping your existing logic)
        composable("attendance") {
            val vm: AttendanceViewModel = viewModel()
            StudentScreen(viewModel = vm, studentName = dashboardViewModel.dashboardData.value.user)
        }
        composable("cr_attendance") {
            val vm: AttendanceViewModel = viewModel()
            Attendance_CR(viewModel = vm)
        }
    }
}