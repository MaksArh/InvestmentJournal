package com.example.finwise.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NewsCard(quote: String, quoteTemp: GlobalQuote) {
    val symbol = quoteTemp.symbol
    val price = quoteTemp.price
    val high = quoteTemp.high
    val low = quoteTemp.low
///////////////////////////////////////////////////////////////////////
//    var symbol by remember { mutableStateOf("Loading . . .") }
//    var high by remember { mutableStateOf("") }
//    var price by remember { mutableStateOf("") }
//    var low by remember { mutableStateOf("") }
//    LaunchedEffect(Unit) {
//        // Запускаем асинхронную операцию
//        launch(Dispatchers.IO) {
//            val result = getQuote(quote)
//
//            // Обновляем состояние в основном потоке
//            withContext(Dispatchers.Main) {
//                symbol = result.globalQuote.symbol
//                high = result.globalQuote.high
//                low = result.globalQuote.low
//                price = result.globalQuote.price
//            }
//        }
//    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column(verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.height(75.dp).padding(PaddingValues(start = 15.dp))) {
                Text(text = symbol, style = MaterialTheme.typography.headlineMedium)
            }
            Column(verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.height(75.dp).padding(8.dp)) {
                Text(text = high, color = Color.Green)
                Text(text = price)
                Text(text = low, color = Color.Red)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun preview(){
//    NewsCard(quote = "TSLA")
//}
