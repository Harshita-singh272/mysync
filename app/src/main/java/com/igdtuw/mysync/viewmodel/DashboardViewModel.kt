package com.igdtuw.mysync.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.igdtuw.mysync.model.DashboardModel

class DashboardViewModel : ViewModel() {
    var dashboardData = mutableStateOf(DashboardModel())
        private set

    fun setUserData(email: String, password: String) {
        val db = FirebaseFirestore.getInstance()

        // Fetch real name from users collection
        db.collection("users").document(email).get()
            .addOnSuccessListener { document ->
                val fullName = document?.getString("name") ?: email.substringBefore("@")

                dashboardData.value = DashboardModel(
                    user = fullName,
                    email = email,
                    branch = document?.getString("branch") ?: "CSE",
                    total =77
                )
            }
            .addOnFailureListener {
                dashboardData.value = DashboardModel(user = email.substringBefore("@"), email = email)
            }
    }
}