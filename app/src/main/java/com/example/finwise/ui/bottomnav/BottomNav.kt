package com.example.finwise.ui.bottomnav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.finwise.models.lesson.getLessonById
import com.example.finwise.models.lesson.getLessons
import com.example.finwise.ui.auth.AuthViewModel
import com.example.finwise.ui.auth.LoginScreen
import com.example.finwise.ui.auth.RegisterScreen
import com.example.finwise.ui.study.LessonPage


@Composable
fun BottomNav(navController: NavController){
    val items = listOf(
        BottomNavItem.Study,
        BottomNavItem.News,
        BottomNavItem.Home
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
    NavHost(navController, startDestination = BottomNavItem.Study.screen_route){
        composable(BottomNavItem.Study.screen_route){
            StudyScreen(navController = navController)
        }
        composable(BottomNavItem.News.screen_route){
            NewsScreen(mainNavController)
        }
        composable(BottomNavItem.Home.screen_route){
            HomeScreen(mainNavController)
        }
        // Добавьте новый маршрут для LessonPage
        composable("lessonPage/{lessonId}") { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId")?.toInt()
            val lesson = lessonId?.let { getLessonById(it) }
            if (lesson != null){
                LessonPage(lesson = lesson)
            }
        }
    }
}