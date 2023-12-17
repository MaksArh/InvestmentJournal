package com.example.finwise.ui.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finwise.models.lesson.Step
import com.example.finwise.models.lesson.getLessons
import com.example.finwise.ui.home.HomeView
import com.example.finwise.ui.study.LessonList
import com.example.finwise.ui.study.LessonPage


@Composable
fun StudyScreen(navController: NavHostController) {
    val lessons = getLessons()
    LessonList(lessons = lessons) { lesson ->
        navController.navigate("lessonPage/${lesson.id}")
    }

}


@Composable
fun NewsScreen () {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .wrapContentSize(Alignment.Center)
    ){
        Text(
            text = "NewsScreen"
        )
    }
}

@Composable
fun HomeScreen () {
    HomeView()
}