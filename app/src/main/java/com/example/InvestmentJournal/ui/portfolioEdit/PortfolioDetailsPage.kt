package com.example.InvestmentJournal.ui.portfolioEdit

import PortfolioViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.InvestmentJournal.models.SharedPreferencesManager
import com.example.InvestmentJournal.ui.auth.ApiService
import com.example.InvestmentJournal.ui.auth.Paper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date

@Composable
fun PortfolioDetailsPage(portfolioId: Int) {
    val viewModel: PortfolioDetailsViewModel = viewModel()
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
class PortfolioDetailsViewModel(private val apiService: ApiService) : ViewModel() {
    val papers = mutableStateListOf<Paper>()

    fun loadPortfolioDetails(portfolioId: Int) {
        viewModelScope.launch {
            try {
                while (true) {
                    val response = apiService.getPortfolioList(portfolioId, SharedPreferencesManager.getUser()) // Замените userId на ваше значение
                    papers.clear()
                    papers.addAll(response)
                    delay(1000) // Обновление каждую секунду
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addPaperToPortfolio(portfolioId: Int, ticker: String, amount: Int, buyTime: Date) {
        viewModelScope.launch {
            try {
                val checkResponse = apiService.checkTicker(ticker)
                if (checkResponse) {
                    val paper = Paper(0, ticker, 0f, 0f, buyTime, portfolioId, amount)
                    apiService.addPaperToPortfolio(portfolioId, SharedPreferencesManager.getUser(), paper) // Замените userId на ваше значение
                    loadPortfolioDetails(portfolioId) // Обновить детали портфеля
                } else {
                    // Обработка случая, когда акции нет
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}



