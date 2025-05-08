package com.fathan0041.bukuin_assesment2_fathan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fathan0041.bukuin_assesment2_fathan.ui.screen.DetailScreen
import com.fathan0041.bukuin_assesment2_fathan.ui.screen.DetailViewScreen
import com.fathan0041.bukuin_assesment2_fathan.ui.screen.KEY_ID_CATATAN
import com.fathan0041.bukuin_assesment2_fathan.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_CATATAN){ type = NavType.LongType}
            )
        ) { navBackStackEntry ->
            val  id = navBackStackEntry.arguments?.getLong(KEY_ID_CATATAN)
            DetailScreen(navController, id)
        }
        composable(
            route = Screen.FormView.route,
            arguments = listOf(navArgument(KEY_ID_CATATAN) { type = NavType.LongType })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_CATATAN)
            DetailViewScreen(navController, id)
        }

    }
}