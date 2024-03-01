package com.example.InvestmentJournal.ui.bottomnav

import PortfolioEditPage
import PortfolioMonitorningPage
import PortfolioMonitorningViewModel
import PortfolioViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.InvestmentJournal.ui.market.MarketPage
import com.example.InvestmentJournal.ui.market.MarketViewModel


@Composable
fun EditScreen(navController: NavHostController) {
    // Прямое получение экземпляра ViewModel
    val viewModel: PortfolioViewModel = viewModel()

    PortfolioEditPage(viewModel, navController)
}


@Composable
fun MonitoringScreen(navController: NavHostController) {
    val viewModel: PortfolioMonitorningViewModel = viewModel()
    PortfolioMonitorningPage(viewModel = viewModel, navHostController = navController)

}


@Composable
fun MarketScreen (navController: NavHostController) {
    val viewModel: MarketViewModel = viewModel()
    MarketPage(viewModel)
}

