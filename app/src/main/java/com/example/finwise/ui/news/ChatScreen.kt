package com.example.finwise.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.finwise.models.SharedPreferencesManager
import com.example.finwise.ui.auth.MessageDto
import com.example.finwise.ui.auth.RetrofitInstance
import com.example.finwise.ui.auth.UserApiService
import com.example.finwise.ui.auth.UserResponse
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var messages by remember { mutableStateOf(listOf<MessageDto>()) }
    var newMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            messages = try {
                RetrofitInstance.api.getAllMessages()
            } catch (e: Exception) {
                emptyList() // Обработка ошибок
            }
            delay(3000)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    Column {
                        Text(
                            text = "${message.userName}:",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp))
                        Text(
                            text = message.message,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            TextField(
                value = newMessage,
                onValueChange = { newMessage = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Напишите сообщение...") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )
            IconButton(onClick = {
                RetrofitInstance.api.sendMessage(MessageDto(userName = SharedPreferencesManager.getUsername(), message = newMessage))
                    .enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {}
                        override fun onFailure(call: Call<Unit>, t: Throwable) {}
                    })
                newMessage = ""
            }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Отправить",
                    tint = MaterialTheme.colorScheme.onSurface // Установка цвета иконки
                )
            }
        }
    }
}
