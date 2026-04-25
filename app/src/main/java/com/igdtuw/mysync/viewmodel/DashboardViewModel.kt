package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.DashboardModel

class DashboardViewModel : ViewModel() {

    // This holds the data that your Dashboard screen will observe
    var dashboardData = mutableStateOf(DashboardModel())
        private set

    /**
     * Called from the Login Screen after a successful Firebase Auth.
     * For now, it populates the model with static dummy data + the user's email.
     */
    // In DashboardViewModel.kt
    fun setUserData(email: String, password: String) {
        // This takes the part before @ (e.g., "aayushree") to show as the name
        val nameFromEmail = email.substringBefore("@")

        dashboardData.value = DashboardModel(
            user = nameFromEmail,
            email = email,
            branch = "CSE-1", // You can change this later
            total = 77,
            thisWeekAnnouncements = 0,
            lastWeekAnnouncements = 7
        )
    }
}