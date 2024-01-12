package com.example.finwise.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.finwise.LocalThemeViewModel
import com.example.finwise.models.SharedPreferencesManager

@Composable
fun HomeView(mainNavController: NavController) {
    val context = LocalContext.current


    val achievements = listOf(
        Achievement("Модный", if (SharedPreferencesManager.getChangeFlag()) 1f else 0f),
        Achievement("1ак", SharedPreferencesManager.getProgress("L1")),
        Achievement("2як", SharedPreferencesManager.getProgress("L2")),
        Achievement("3чок", SharedPreferencesManager.getProgress("L3")),
        Achievement("4чок", SharedPreferencesManager.getProgress("L4")),
        Achievement("5ёк", SharedPreferencesManager.getProgress("L5"))
    )
    val themeViewModel = LocalThemeViewModel.current

    Scaffold(
        content = { innerPadding ->
            Column (modifier = Modifier.padding(innerPadding)){
                UserCard(
                    onSettingsClick = { themeViewModel.toggleTheme()
                                      SharedPreferencesManager.setChangeFlag()},
                    onLogoutClick = { onLogoutClick(mainNavController) }
                )

                AchievementsList(
                    achievements = achievements,
                    onAchievementClick = { /* Обработка нажатия на ачивку */ } //не надо
                )
            }
        }
    )
}


fun onLogoutClick(navController: NavController){
    SharedPreferencesManager.setLoggedIn(false)
    SharedPreferencesManager.setUsername("")
    navController.navigate("LoginScreenRoute")
}