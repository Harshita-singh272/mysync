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
    fun setUserData(email: String, password: String) {
        dashboardData.value = DashboardModel(
            user = email.substringBefore("@"), // Takes 'aayushree' from 'aayushree@igdtuw.ac.in'
            email = email,
            branch = "CSE-1", // You can update this based on email patterns later
            total = 77,
            thisWeekAnnouncements = 0,
            lastWeekAnnouncements = 7
        )
    }
}