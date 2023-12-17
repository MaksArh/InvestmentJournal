package com.example.finwise.ui.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.finwise.models.lesson.getLessons
import com.example.finwise.ui.home.HomeView
import com.example.finwise.ui.news.NewsView
import com.example.finwise.ui.study.LessonList


@Composable
fun StudyScreen(navController: NavHostController) {
    val lessons = getLessons()
    LessonList(lessons = lessons) { lesson ->
        navController.navigate("lessonPage/${lesson.id}")
    }

}


@Composable
fun NewsScreen () {
    NewsView()
}

@Composable
fun HomeScreen () {
    HomeView()
}