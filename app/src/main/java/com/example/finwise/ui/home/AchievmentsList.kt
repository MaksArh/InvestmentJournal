package com.example.finwise.ui.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable

//, val icon: Int
data class Achievement(val name: String, val progress: Float)

@Composable
fun AchievementsList(
    achievements: List<Achievement>,
    onAchievementClick: (Achievement) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(achievements) { achievement ->
            AchievementCard(
                achievement = achievement,
                onAchievementClick = onAchievementClick
            )
        }
    }
}