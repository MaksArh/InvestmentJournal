package com.example.finwise.models.lesson

import androidx.compose.runtime.MutableState
import com.example.finwise.ui.study.LessonExam

open class Step(var id: Int, var title: String, var body: String, var completed: MutableState<Boolean>, var test: LessonExam?=null)

