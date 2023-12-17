package com.example.finwise.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewsView(){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        item {
            Text(
                text = "Листинг",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp)
            )
            Divider(modifier = Modifier.padding(bottom=8.dp ), color = MaterialTheme.colorScheme.primary)

        }

        item{ Spacer(modifier = Modifier.fillMaxWidth()) }
        item {
            NewsCard("APPL",GlobalQuote(symbol = "APPL",price="2.2",high="16.6",low="1.2"))
            NewsCard("GZPM",GlobalQuote(symbol = "GZPM",price="25.3",high="16.8",low="19.6"))
            NewsCard("YNDX",GlobalQuote(symbol = "YNDX",price="356.0",high="16.4",low="14.5"))
            NewsCard("SBOL",GlobalQuote(symbol = "SBOL",price="25.1",high="14.2",low="13.1"))
            NewsCard("MDRN",GlobalQuote(symbol = "MDRN",price="258.0",high="169.2",low="148.8"))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun preview(){
//    NewsCard(quote = "TSLA")
//}
