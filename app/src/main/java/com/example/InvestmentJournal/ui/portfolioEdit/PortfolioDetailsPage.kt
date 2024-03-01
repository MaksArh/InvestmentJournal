package com.example.InvestmentJournal.ui.portfolioEdit

import PortfolioViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.text.DateFormat

@Composable
fun PortfolioDetailsPage(portfolioId: Int, viewModel: PortfolioViewModel) {
    // Диалоговое окно для добавления бумаги в портфель
    var showDialog by remember { mutableStateOf(false) }

    // Асинхронная загрузка деталей портфеля при первом рендере страницы
    LaunchedEffect(portfolioId) {
        viewModel.loadPortfolioDetails(portfolioId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Заголовок страницы с названием портфеля (заглушка, нужен запрос для получения названия)
        Text("Портфель $portfolioId", style = MaterialTheme.typography.h5)
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Отображение карточек с бумагами портфеля
        viewModel.papers.forEach { paper ->
            PaperCard(paper = paper)
        }

        // Кнопка для добавления новой бумаги
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Добавить бумагу")
            }
        }

        // Диалоговое окно для добавления новой бумаги
        if (showDialog) {
            AddPaperDialog(
                portfolioId = portfolioId,
                onDismiss = { showDialog = false },
                viewModel = viewModel
            )
        }
    }
}



