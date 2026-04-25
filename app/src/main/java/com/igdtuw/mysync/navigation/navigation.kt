package com.igdtuw.mysync.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igdtuw.mysync.screen.* // This imports ALL screens at once to avoid missing ones
import com.igdtuw.mysync.viewmodel.AnnouncementViewModel
import com.igdtuw.mysync.viewmodel.AssignmentViewModel
import com.igdtuw.mysync.viewmodel.AttendanceViewModel
import com.igdtuw.mysync.viewmodel.DashboardViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dashboardViewModel: DashboardViewModel = viewModel()
    val announcementViewModel: AnnouncementViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splashscreen"
    ) {
        composable("login") { Login(navController, dashboardViewModel) }
        composable("splashscreen") { SplashScreen(navController) }
        composable("student") { Dash_main(navController, dashboardViewModel) }
        composable("cr") { Dash_CR(navController, dashboardViewModel) }
        composable("student_cr") { Dash_main_Cr(navController, dashboardViewModel) }

        composable("assignment") {
            val vm: AssignmentViewModel = viewModel()
            AssignmentScreen(viewModel = vm, onEditClick = { })
        }

        composable("announcements") {
            ViewAnnouncementScreen(announcementViewModel)
        }

        // --- ATTENDANCE ROUTES ---


        composable(route = "attendance") {
            val vm: AttendanceViewModel = viewModel()

            val nameForSearch = dashboardViewModel.dashboardData.value.user

            StudentScreen(
                viewModel = vm,
                studentName = nameForSearch
            )
        }


        composable("cr_attendance") {
            val vm: AttendanceViewModel = viewModel()
            Attendance_CR(viewModel = vm)
        }

        // --- ANNOUNCEMENT & ASSIGNMENT FOR CR ---

        composable("cr_assignment") {
            val vm: AssignmentViewModel = viewModel()
            AdminAssignmentScreen(viewModel = vm, onBack = { navController.popBackStack() })
        }

        composable("cr_announcements") {
            PostAnnouncementScreen(announcementViewModel)
        }
    }
}