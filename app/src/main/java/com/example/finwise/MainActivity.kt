package com.example.finwise

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finwise.models.SharedPreferencesManager
import com.example.finwise.models.lesson.getLessonById
import com.example.finwise.ui.auth.AuthViewModel
import com.example.finwise.ui.auth.AuthViewModelFactory
import com.example.finwise.ui.auth.LoginScreen
import com.example.finwise.ui.auth.RegisterScreen
import com.example.finwise.ui.bottomnav.BottomNav
import com.example.finwise.ui.bottomnav.BottomNavItem
import com.example.finwise.ui.bottomnav.HomeScreen
import com.example.finwise.ui.bottomnav.NavigationGraph
import com.example.finwise.ui.bottomnav.NewsScreen
import com.example.finwise.ui.bottomnav.StudyScreen
import com.example.finwise.ui.news.ChatScreen
import com.example.finwise.ui.study.LessonPage
import com.example.finwise.ui.theme.FinWiseTheme
import com.example.finwise.ui.theme.ThemeViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferencesManager.init(this)
        scheduleNotification(this)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            CompositionLocalProvider(LocalThemeViewModel provides themeViewModel) {
                FinWiseTheme(darkTheme = themeViewModel.isDarkTheme) {
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
        composable("ChatScreenRoute"){
            ChatScreen()
        }
    }
}

fun scheduleNotification(context: Context) {
    val alarmManager = ContextCompat.getSystemService(context, AlarmManager::class.java) as AlarmManager
    val intent = Intent(context, MyAlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val intervalMillis = 15 * 60 * 1000L // 15 минут

    alarmManager.setInexactRepeating(
        AlarmManager.RTC_WAKEUP,
        System.currentTimeMillis(),
        intervalMillis,
        pendingIntent
    )
}

//@Preview(showBackground = true)
//@Composable
//fun BottomNavigationPreview() {
//    MainScreenView()
//}