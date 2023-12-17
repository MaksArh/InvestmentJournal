package com.example.finwise.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun HomeView() {
    val context = LocalContext.current

    // Пример данных для ачивок
    val achievements = listOf(
        Achievement("Achievement 1", 0.7f),
        Achievement("Achievement 2", 0.5f),
        Achievement("Achievement 3", 0.9f),
        // Добавьте свои данные для ачивок
    )

    Scaffold(
        content = { innerPadding ->
            Column (modifier = Modifier.padding(innerPadding)){
                UserCard(
                    userName = "Дэнчик",
                    onEditNameClick = { /* Обработка изменения имени пользователя */ },
                    onSettingsClick = { /* Обработка нажатия на настройки */ }
                )

                AchievementsList(
                    achievements = achievements,
                    onAchievementClick = { /* Обработка нажатия на ачивку */ }
                )
            }
        }
    )
}