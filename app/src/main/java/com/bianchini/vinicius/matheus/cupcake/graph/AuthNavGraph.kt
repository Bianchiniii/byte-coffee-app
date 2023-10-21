package com.bianchini.vinicius.matheus.cupcake.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bianchini.vinicius.matheus.cupcake.feature.auth.commun.viewmodel.AuthViewModel
import com.bianchini.vinicius.matheus.cupcake.feature.auth.login.presentation.LoginScreen
import com.bianchini.vinicius.matheus.cupcake.feature.auth.signup.presentation.SignUpScreen
import com.bianchini.vinicius.matheus.cupcake.feature.auth.splash.presentation.AnimatedSplashScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreenRoutes.Splash.route,
    ) {
        composable(route = AuthScreenRoutes.Splash.route) {
            val authViewModel = it.sharedAuthViewModel<AuthViewModel>(navController = navController)
            AnimatedSplashScreen(
                navController = navController,
                authViewModel
            )
        }
        composable(route = AuthScreenRoutes.SignUp.route) {
            val authViewModel = it.sharedAuthViewModel<AuthViewModel>(navController = navController)
            SignUpScreen(
                navController = navController,
                authViewModel
            )
        }
        composable(route = AuthScreenRoutes.Login.route) {
            val authViewModel = it.sharedAuthViewModel<AuthViewModel>(navController = navController)
            LoginScreen(
                authViewModel
            )
        }
    }
}

sealed class AuthScreenRoutes(val route: String) {
    object Splash : AuthScreenRoutes("splash")
    object SignUp : AuthScreenRoutes("sign_up")
    object Login : AuthScreenRoutes("login")
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedAuthViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}