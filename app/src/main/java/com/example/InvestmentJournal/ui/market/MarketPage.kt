package com.example.InvestmentJournal.ui.market

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.InvestmentJournal.ui.auth.ApiService
import com.example.InvestmentJournal.ui.auth.MarketPaper
import kotlinx.coroutines.launch


@Composable
fun MarketPage(viewModel: MarketViewModel) {

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Мониторинг акций", style = MaterialTheme.typography.h5)
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(viewModel.papers) { paper ->
                PaperCard(paper = paper)
            }
        }
    }
}


class MarketViewModel(private val apiService: ApiService) : ViewModel() {
    private val _papers = mutableStateListOf<MarketPaper>()
    val papers: List<MarketPaper> = _papers

    init {
        loadPapers()
    }
    fun loadPapers() {
        viewModelScope.launch {
            try {
                val papersList = apiService.getMarketPapers()
                _papers.clear()
                _papers.addAll(papersList)
            } catch (e: Exception) {
                Log.e("MonitoringViewModel", "Error loading papers", e)
            }
        }
    }
}

