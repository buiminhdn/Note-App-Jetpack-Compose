package com.example.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.ui.detail.DetailScreen
import com.example.noteapp.ui.home.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route
    ) {
        composable(route = NavScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = "${NavScreen.DetailScreen.route}?noteId={noteId}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            DetailScreen(
                navController = navController,
                noteId = if (noteId == -1) null else noteId
            )
        }

    }
}