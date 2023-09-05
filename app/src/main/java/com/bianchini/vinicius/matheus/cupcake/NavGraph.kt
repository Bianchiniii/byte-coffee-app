package com.bianchini.vinicius.matheus.cupcake

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bianchini.vinicius.matheus.cupcake.feature.signup.SignUpScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoutes.Splash.route
    ) {
        composable(route = ScreenRoutes.Splash.route) {
            AnimatedSplashScreen(navController = navHostController)
        }
        composable(route = ScreenRoutes.SignUp.route) {
            SignUpScreen()
        }
    }
}