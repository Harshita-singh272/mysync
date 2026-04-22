package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.DashboardModel

class DashboardViewModel : ViewModel() {

    var dashboardData = mutableStateOf(DashboardModel())
        private set

    init {
        loadDummyData() // later replace with Firebase
    }

    private fun loadDummyData() {
        dashboardData.value = DashboardModel(
            user = "06901",
            email = "harshitasinghixa@gmail.com",
            branch = "CSE-1",
            total = 77,
            thisWeekAnnouncements = 0,
            lastWeekAnnouncements = 7
        )
    }

    // 🔥 Later replace this with Firebase fetch
    fun updateFromDatabase(data: DashboardModel) {
        dashboardData.value = data
    }
}