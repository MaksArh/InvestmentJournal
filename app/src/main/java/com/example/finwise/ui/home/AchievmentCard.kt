package com.example.finwise.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AchievementCard(
    achievement: Achievement,
    onAchievementClick: (Achievement) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onAchievementClick(achievement) },
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = achievement.name, textAlign = TextAlign.Center)
            // Круглый прогресс бар
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp),
                progress = achievement.progress,
                color = MaterialTheme.colorScheme.primary
            )

            // Иконка ачивки
            Spacer(modifier = Modifier.height(8.dp))
//            Icon(
//                painter = painterResource(id = achievement.icon),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(32.dp)
//                    .clip(CircleShape)
//            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun preview(){
    AchievementCard(achievement = Achievement("epi",1f), onAchievementClick ={} )
}