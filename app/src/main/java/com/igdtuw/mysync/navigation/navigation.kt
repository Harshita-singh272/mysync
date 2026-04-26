package com.igdtuw.mysync.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
<<<<<<< Updated upstream
import androidx.navigation.compose.rememberNavController
import com.igdtuw.mysync.screen.* import com.igdtuw.mysync.viewmodel.*
=======
import com.igdtuw.mysync.screen.AdminAssignmentScreen
import com.igdtuw.mysync.screen.AssignmentCRScreen
import com.igdtuw.mysync.screen.AssignmentScreen
import com.igdtuw.mysync.screen.CRScreen
import com.igdtuw.mysync.screen.Dash_main_Cr
import com.igdtuw.mysync.screen.PostAnnouncementScreen
import com.igdtuw.mysync.screen.SplashScreen
import com.igdtuw.mysync.screen.StudentScreen
import com.igdtuw.mysync.screen.Syllabusblock
import com.igdtuw.mysync.screen.Timetableblock
import com.igdtuw.mysync.screen.ViewAnnouncementScreen
import com.igdtuw.mysync.viewmodel.AssignmentViewModel
import com.igdtuw.mysync.viewmodel.AnnouncementViewModel
import com.igdtuw.mysync.viewmodel.AttendanceViewModel
import com.igdtuw.mysync.viewmodel.DashboardViewModel
import kotlin.collections.mutableListOf

>>>>>>> Stashed changes

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dashboardViewModel: DashboardViewModel = viewModel()
    val announcementViewModel: AnnouncementViewModel = viewModel()
    val assignmentViewModel: AssignmentViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "splashscreen"
    ) {
<<<<<<< Updated upstream
        composable("splashscreen") { SplashScreen(navController) }
        composable("login") { Login(navController, dashboardViewModel) }
        composable("student") { Dash_main(navController, dashboardViewModel) }
        composable("cr") { Dash_CR(navController, dashboardViewModel) }
=======
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
                viewModel = assignmentViewModel,
                onEditClick = {}
            )
        }
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======

        composable("cr_assignment") {
            AdminAssignmentScreen(
                viewModel = assignmentViewModel,
                onBack = { navController.popBackStack() }
            )
        }

>>>>>>> Stashed changes
        composable("cr_attendance") {
            val vm: AttendanceViewModel = viewModel()
            Attendance_CR(viewModel = vm)
        }
        composable("assignmentstudent_cr"){
            AssignmentCRScreen(
                navController = navController,
                viewModel = assignmentViewModel,
                onEditClick = {}
            )
        }

    }
}