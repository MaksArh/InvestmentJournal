package com.example.finwise.models.lesson

import androidx.compose.runtime.MutableState

open class Step(var id: Int, var title: String, var body: String, var completed: MutableState<Boolean>)

