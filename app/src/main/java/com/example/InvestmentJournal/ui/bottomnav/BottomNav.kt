package com.example.InvestmentJournal.ui.bottomnav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNav(navController: NavController){
    val items = listOf(
        BottomNavItem.Edit,
        BottomNavItem.Monitorning,
        BottomNavItem.Market
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title)},
                label = { Text(text = item.title, fontSize = 9.sp)},
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.primary.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                inclusive = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController,mainNavController: NavController){
    NavHost(navController, startDestination = BottomNavItem.Edit.screen_route){
        composable(BottomNavItem.Edit.screen_route){
            EditScreen(navController = navController)
        }
        composable(BottomNavItem.Monitorning.screen_route){
            MonitorningScreen(mainNavController)
        }
        composable(BottomNavItem.Market.screen_route){
            MarketScreen(mainNavController)
        }
        composable("portfolioDetails/{portfolioId}") { backStackEntry ->
            PortfolioDetailsScreen(portfolioId = backStackEntry.arguments?.getInt("portfolioId"))
        }
    }
}