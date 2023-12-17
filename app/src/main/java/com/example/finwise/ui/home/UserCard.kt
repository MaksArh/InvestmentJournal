package com.example.finwise.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finwise.R

@Composable
fun UserCard(
    userName: String,
    onEditNameClick: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier
            .fillMaxHeight(0.5f)) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f))
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(5f),
                contentAlignment = Alignment.Center
            ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        modifier = Modifier
                            .size(225.dp)
                            .clip(CircleShape),
                        alignment = Alignment.Center
                    )
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    modifier = Modifier
                        .size(225.dp)
                        .clip(CircleShape),
                    alignment = Alignment.Center
                )

                // Колонка для текста и иконки
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 16.dp)
                ){Row(verticalAlignment = Alignment.CenterVertically){
                    // Имя пользователя
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )

                    // Кнопка настроек
                    IconButton(
                        onClick = { /* Обработка нажатия на кнопку изменения имени */ }
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }}
                }
                }
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                IconButton(
                    onClick = onSettingsClick
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MyPreview(){
    UserCard(userName = "Гоги", onEditNameClick = {}) {
        
    }
}