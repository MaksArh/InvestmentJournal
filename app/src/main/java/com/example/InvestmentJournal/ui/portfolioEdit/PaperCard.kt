package com.example.InvestmentJournal.ui.portfolioEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.InvestmentJournal.ui.auth.Paper

@Composable
fun PaperCard(paper: Paper) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = paper.name, style = MaterialTheme.typography.h6)
            Text(text = "Количество: ${paper.amount}")
            Text(text = "Начальная цена: ${paper.startPrice}")
            Text(text = "Время покупки: ${paper.buyTime}")
        }
    }
}
