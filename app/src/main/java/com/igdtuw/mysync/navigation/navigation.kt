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


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dashboardViewModel: DashboardViewModel = viewModel()
    val announcementViewModel: AnnouncementViewModel = viewModel()

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
            val vm: com.igdtuw.mysync.viewmodel.AssignmentViewModel = viewModel()

            AssignmentScreen(
                viewModel = vm,
                onEditClick = {

                }
            )
        }

        composable("announcements") {
            ViewAnnouncementScreen(announcementViewModel)
        }
        composable(route = "attendance") {
            val vm: AttendanceViewModel = viewModel()
            StudentScreen(
                viewModel = vm
            )
        }

        composable("cr_assignment") {
            val vm: AssignmentViewModel = viewModel()

            AdminAssignmentScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }

        composable("cr_attendance") {
            val vm: AttendanceViewModel = viewModel()
            CRScreen(viewModel = vm)
        }

        composable("cr_announcements") {
            PostAnnouncementScreen(announcementViewModel)
        }
        composable("student_cr") {
            Dash_main_Cr(navController, dashboardViewModel)
        }
    }
}





