package com.igdtuw.mysync.viewmodel

import androidx.lifecycle.ViewModel
import com.igdtuw.mysync.model.AssignmentItem
import com.igdtuw.mysync.model.SubjectData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AssignmentViewModel : ViewModel() {

    private val _subjects = MutableStateFlow<List<SubjectData>>(emptyList())
    val subjects: StateFlow<List<SubjectData>> = _subjects

    fun setSubjects(data: List<SubjectData>) {
        _subjects.value = data
    }

    //    fun addAssignment(subjectName: String, item: AssignmentItem) {
//        _subjects.value = _subjects.value.map { subject ->
//            if (subject.name == subjectName) {
//                subject.copy(
//                    theory = subject.theory.toMutableList().apply { add(item) }
//                )
//            } else subject
//        }
//    }
//}
    fun addAssignment(
        subjectName: String,
        item: AssignmentItem,
        isLab: Boolean
    ) {
        _subjects.value = _subjects.value.map { subject ->
            if (subject.name == subjectName) {

                if (isLab) {
                    subject.copy(
                        lab = subject.lab.toMutableList().apply { add(item) }
                    )
                } else {
                    subject.copy(
                        theory = subject.theory.toMutableList().apply { add(item) }
                    )
                }

            } else subject
        }
    }
}