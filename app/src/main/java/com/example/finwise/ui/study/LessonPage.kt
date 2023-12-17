package com.example.finwise.ui.study

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finwise.models.lesson.Lesson
import com.example.finwise.models.lesson.Step
import com.example.finwise.models.lesson.confirmStep

@Composable
fun LessonPage(lesson: Lesson) {
    var selectedStepIndex by remember { mutableStateOf(0) }
    Column {
        Text(text = lesson.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))

        LazyRow {
            item{Spacer(modifier = Modifier.width(8.dp))}
            items(lesson.steps.size) { index ->
                StepSquare(step = lesson.steps[index],
                    isSelected = index == selectedStepIndex,
                    onStepClick = {
                        selectedStepIndex = index
                        confirmStep(lessonId = lesson.id, stepId =index )})
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        LazyColumn {val step = lesson.steps[selectedStepIndex]
            item {
                StepContent(step = step)
            }
        }
    }
}

