package com.example.finwise.ui.study

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finwise.models.lesson.Step

@Composable
fun StepSquare(step: Step, isSelected: Boolean, onStepClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(if (step.completed.value)
                MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.small)
            .clickable { onStepClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = (step.id+1).toString(), // Нумерация с 1, а не с 0
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StepContent(step: Step) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)


    ) {
        item {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = step.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Divider(modifier = Modifier.padding(bottom=8.dp ),color = MaterialTheme.colorScheme.primary)
        }
        item{
            Text(color = MaterialTheme.colorScheme.onSurface,text = step.body, style = MaterialTheme.typography.bodyLarge)

        }
    }
}