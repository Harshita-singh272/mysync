package com.igdtuw.mysync.model


data class Student(
    val id: Int,
    val name: String,
    val isPresent: Boolean? = null
)

data class SubjectAttendance(
    val name: String,
    val attended: Int,
    val total: Int
) {
    val percentage: Float = if (total > 0) (attended.toFloat() / total) * 100 else 0f
}