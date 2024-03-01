package com.example.InvestmentJournal.ui.bottomnav

import com.example.InvestmentJournal.R

sealed class BottomNavItem (var title:String, var icon:Int, var screen_route:String){
    object Edit : BottomNavItem("Edit", R.drawable.ic_school,"edit")
    object Monitorning : BottomNavItem("Monitorning", R.drawable.ic_finance,"monitorning")
    object Market : BottomNavItem("Market", R.drawable.ic_home_account,"market")
}