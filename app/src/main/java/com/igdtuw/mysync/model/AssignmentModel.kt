package com.igdtuw.mysync.model

data class AssignmentItem(
    var title: String,
    var link: String
)

data class SubjectData(
    var name: String,
    var theory: MutableList<AssignmentItem>,
    var lab: MutableList<AssignmentItem>
)