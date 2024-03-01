package com.example.InvestmentJournal.ui.portfolioEdit

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AddPortfolioDialog(onDismiss: () -> Unit, onCreate: (String) -> Unit) {
    var portfolioName by remember { mutableStateOf("") }

    androidx.compose.material.AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Создать портфель") },
        text = {
            TextField(
                value = portfolioName,
                onValueChange = { portfolioName = it },
                label = { Text("Название портфеля") }
            )
        },
        confirmButton = {
            androidx.compose.material.Button(onClick = { onCreate(portfolioName) }) {
                Text("Создать")
            }
        },
        dismissButton = {
            androidx.compose.material.Button(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}