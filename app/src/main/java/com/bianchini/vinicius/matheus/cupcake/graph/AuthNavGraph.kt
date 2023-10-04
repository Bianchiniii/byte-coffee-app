package com.bianchini.vinicius.matheus.cupcake.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bianchini.vinicius.matheus.cupcake.feature.login.LoginScreen
import com.bianchini.vinicius.matheus.cupcake.feature.signup.SignUpScreen
import com.bianchini.vinicius.matheus.cupcake.feature.splash.AnimatedSplashScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreenRoutes.Splash.route,
    ) {
        composable(route = AuthScreenRoutes.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = AuthScreenRoutes.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = AuthScreenRoutes.Login.route) {
            LoginScreen()
        }
    }
}

sealed class AuthScreenRoutes(val route: String) {
    object Splash : AuthScreenRoutes("splash")
    object SignUp : AuthScreenRoutes("sign_up")
    object Login : AuthScreenRoutes("login")
}