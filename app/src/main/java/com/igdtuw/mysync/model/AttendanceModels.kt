package com.igdtuw.mysync.model

data class AttendanceRecord(
    val studentName: String,
    val enrollmentNo: String,
    val email: String,
    val isPresent: Boolean = false
)

data class SubjectAttendance(
    val name: String,
    val attended: Int,
    val total: Int,
    val percentage: Float = if (total > 0) (attended.toFloat() / total) * 100 else 0f
)