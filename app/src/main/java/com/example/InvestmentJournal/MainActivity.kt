package com.example.InvestmentJournal

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.InvestmentJournal.models.SharedPreferencesManager
import com.example.InvestmentJournal.ui.auth.AuthViewModel
import com.example.InvestmentJournal.ui.auth.AuthViewModelFactory
import com.example.InvestmentJournal.ui.auth.LoginScreen
import com.example.InvestmentJournal.ui.auth.RegisterScreen
import com.example.InvestmentJournal.ui.bottomnav.BottomNav
import com.example.InvestmentJournal.ui.bottomnav.NavigationGraph
import com.example.InvestmentJournal.ui.theme.InvestmentJournalTheme
import com.example.InvestmentJournal.ui.theme.ThemeViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferencesManager.init(this)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            CompositionLocalProvider(LocalThemeViewModel provides themeViewModel) {
                InvestmentJournalTheme(darkTheme = themeViewModel.isDarkTheme) {
                    val navController = rememberNavController()
                    val factory = AuthViewModelFactory(this)
                    val authViewModel: AuthViewModel = viewModel(factory = factory)
                    MainNavigationGraph(navController = navController, authView = authViewModel)
                }
            }
        }
    }
}
val LocalThemeViewModel = compositionLocalOf<ThemeViewModel> { error("No ThemeViewModel found!") }
@Composable
fun MainScreenView(mainNavController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(navController = navController)},
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ){
            NavigationGraph(navController = navController,mainNavController)
    }
    }
}

@Composable
fun MainNavigationGraph(navController: NavHostController, authView: AuthViewModel){
    NavHost(navController, startDestination = "LoginScreenRoute"){
        composable("MainScreenRoute"){
            MainScreenView(navController)
        }
        composable("LoginScreenRoute"){
            LoginScreen(navController, authView)
        }
        composable("RegisterScreenRoute"){
            RegisterScreen(navController, authView)
        }
    }
}
