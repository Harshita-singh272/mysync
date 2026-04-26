package com.igdtuw.mysync.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igdtuw.mysync.screen.*
import com.igdtuw.mysync.viewmodel.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Shared ViewModels
    val dashboardViewModel: DashboardViewModel = viewModel()
    val announcementViewModel: AnnouncementViewModel = viewModel()
    val assignmentViewModel: AssignmentViewModel = viewModel()

    NavHost(
        navController = navController,
        // Match the route name exactly as defined below
        startDestination = "splash"
    ) {
        // --- AUTH & STARTUP ---
        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            Login(navController, dashboardViewModel)
        }

        // --- DASHBOARDS ---
        composable("student") {
            Dash_main(navController, dashboardViewModel)
        }

        composable("cr") {
            Dash_CR(navController, dashboardViewModel)
        }

        // --- STUDENT FEATURES ---
        composable("announcements") {
            Announcement_Student(announcementViewModel)
        }

        composable("assignment") {
            AssignmentScreen(
                viewModel = assignmentViewModel,
                onEditClick = {}
            )
        }

        composable("attendance") {
            val vm: AttendanceViewModel = viewModel()
            // Pulling student name from dashboard state
            StudentScreen(
                viewModel = vm,
                studentName = dashboardViewModel.dashboardData.value.user
            )
        }

        // --- CR FEATURES ---
        composable("cr_announcements") {
            Announcement_CR(announcementViewModel)
        }

        composable("cr_assignment") {
            AdminAssignmentScreen(
                viewModel = assignmentViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("cr_attendance") {
            val vm: AttendanceViewModel = viewModel()
            Attendance_CR(viewModel = vm)
        }

        composable("assignmentstudent_cr") {
            AssignmentCRScreen(
                navController = navController,
                viewModel = assignmentViewModel,
                onEditClick = {}
            )
        }
    }
}