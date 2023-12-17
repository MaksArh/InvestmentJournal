package com.example.finwise.ui.study

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finwise.models.lesson.Lesson


@Composable
fun LessonList(lessons: List<Lesson>, onLessonClick: (Lesson) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Уроки по сливу депо",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
        items(lessons) { lesson ->
            LessonCard(lesson = lesson, modifier = Modifier.clickable {
                onLessonClick(lesson)
            })
        }
    }
}