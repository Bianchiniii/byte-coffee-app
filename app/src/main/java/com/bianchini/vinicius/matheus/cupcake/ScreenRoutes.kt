package com.bianchini.vinicius.matheus.cupcake

sealed class ScreenRoutes(val route: String) {
    object Splash : ScreenRoutes("splash")
    object SignUp : ScreenRoutes("sign_up")
    object Login : ScreenRoutes("login")
}