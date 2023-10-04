package com.bianchini.vinicius.matheus.cupcake.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bianchini.vinicius.matheus.cupcake.feature.home.HomeScreen

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navHostController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}