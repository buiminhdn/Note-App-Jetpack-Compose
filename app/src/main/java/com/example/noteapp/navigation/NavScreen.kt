package com.example.noteapp.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen("home_screen")
    object DetailScreen : NavScreen("detail_screen")
}