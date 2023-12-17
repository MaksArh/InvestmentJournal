package com.example.finwise.models.lesson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf

open class Lesson(var id: Int, var progress: MutableState<Float>, var title: String, var steps: List<Step>)
var steps1 = listOf(
    Step(0,"1","", mutableStateOf(false)),
    Step(1,"2","", mutableStateOf(false)),
    Step(2,"3","", mutableStateOf(false)),
    Step(3,"4","", mutableStateOf(false)))
object lesson1 : Lesson(0, mutableFloatStateOf(0f),"Валюты",steps1)

var steps2 = listOf(
    Step(0,"1","", mutableStateOf(false)),
    Step(1,"2","", mutableStateOf(false)),
    Step(2,"3","", mutableStateOf(false)),
    Step(3,"4","", mutableStateOf(false)))
object lesson2 : Lesson(1, mutableFloatStateOf(0f),"Акции",steps2)

var steps3 = listOf(
    Step(0,"","", mutableStateOf(false)),
    Step(1,"","", mutableStateOf(false)),
    Step(2,"","", mutableStateOf(false)),
    Step(3,"","", mutableStateOf(false)))
object lesson3 : Lesson(2, mutableFloatStateOf(0f),"Облигации",steps3)

var steps4 = listOf(
    Step(0,"","", mutableStateOf(false)),
    Step(1,"","", mutableStateOf(false)),
    Step(2,"","", mutableStateOf(false)),
    Step(3,"","", mutableStateOf(false)))
object lesson4 : Lesson(3, mutableFloatStateOf(0f),"ИИС",steps4)

var steps5 = listOf(
    Step(0,"","", mutableStateOf(false)),
    Step(1,"","", mutableStateOf(false)),
    Step(2,"","", mutableStateOf(false)),
    Step(3,"","", mutableStateOf(false)))
object lesson5 : Lesson(4,mutableFloatStateOf(0f),"Трейдинг",steps5)

var lessons = listOf(lesson1,lesson2,lesson3,lesson4,lesson5)

@Composable
fun getLessons(): List<Lesson>{
    return lessons
}
@Composable
fun getLessonById(id: Int): Lesson{
    return lessons[id]
}

@Composable
fun getSteps(id: Int): List<Step> {
    return lessons[id].steps
}

fun confirmStep(lessonId: Int, stepId: Int){
    lessons[lessonId].steps[stepId].completed.value=true
    lessons[lessonId].progress.value= calculateCompletionRatio(lessonId)

}
fun calculateCompletionRatio(lessonId: Int): Float {
    var totalSteps = lessons[lessonId].steps.size+1

    var completedSteps = 0


    for (step in lessons[lessonId].steps) {
        if (step.completed.value) {
            completedSteps++
        }
    }
    return completedSteps.toFloat() / totalSteps.toFloat()
}