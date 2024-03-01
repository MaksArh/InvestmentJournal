package com.example.InvestmentJournal.ui.bottomnav

import PortfolioEditPage
import PortfolioViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun EditScreen(navController: NavHostController) {
    // Прямое получение экземпляра ViewModel
    val viewModel: PortfolioViewModel = viewModel()

    PortfolioEditPage(viewModel, navController)
}


@Composable
fun MonitorningScreen(navController: NavController) {


}


@Composable
fun MarketScreen (navController: NavController) {
}

