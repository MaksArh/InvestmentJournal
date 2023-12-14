package com.example.finwise.ui.bottomnav

import com.example.finwise.R

sealed class BottomNavItem (var title:String, var icon:Int, var screen_route:String){
    object Study : BottomNavItem("Study", R.drawable.ic_school,"study")
    object News : BottomNavItem("News", R.drawable.ic_finance,"news")
    object Home : BottomNavItem("Home", R.drawable.ic_home_account,"home")
}