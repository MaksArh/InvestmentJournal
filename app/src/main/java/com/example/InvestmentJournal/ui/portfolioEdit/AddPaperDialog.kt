package com.example.InvestmentJournal.ui.portfolioEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AddPaperDialog(portfolioId: Int, onDismiss: () -> Unit, viewModel: PortfolioDetailsViewModel) {
    var ticker by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var buyTime by remember { mutableStateOf(getCurrentDateString()) } // Используется для инициализации значением по умолчанию

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Добавить бумагу") },
        text = {
            Column {
                TextField(value = ticker, onValueChange = { ticker = it }, label = { Text("Тикер") })
                TextField(value = amount, onValueChange = { amount = it }, label = { Text("Количество") })
                TextField(value = buyTime, onValueChange = { buyTime = it }, label = { Text("Дата покупки") })
            }
        },
        confirmButton = {
            Button(onClick = {
                val parsedDate = parseDate(buyTime) ?: Calendar.getInstance().time // Если дата неверная, используем текущую дату
                if (ticker.isNotEmpty() && amount.toIntOrNull() != null) {
                    viewModel.addPaperToPortfolio(portfolioId, ticker, amount.toInt(), parsedDate)
                    onDismiss()
                }
            }) {
                Text("Добавить")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}


fun parseDate(dateStr: String): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return try {
        format.parse(dateStr)
    } catch (e: Exception) {
        null
    }
}

fun getCurrentDateString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date()) // Использует текущую дату
}