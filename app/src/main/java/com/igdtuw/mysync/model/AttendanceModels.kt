package com.igdtuw.mysync.model

data class Student(
    val id: Int,
    val name: String,
    val rollNumber: String,
    val isPresent: Boolean? = null
)