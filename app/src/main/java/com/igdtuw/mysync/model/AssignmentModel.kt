package com.igdtuw.mysync.model

data class AssignmentItem(
    var id: String = "",
    var title: String = "",
    var link: String = "",
    var description: String = "",
    var subject: String = "",   // ✅ keep this
    var type: String = ""
)