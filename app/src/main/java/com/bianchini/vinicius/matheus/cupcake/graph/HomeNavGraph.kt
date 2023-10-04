package com.bianchini.vinicius.matheus.cupcake.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bianchini.vinicius.matheus.cupcake.feature.cart.CartScreen
import com.bianchini.vinicius.matheus.cupcake.feature.home.ContentHomeScreen
import com.bianchini.vinicius.matheus.cupcake.feature.profile.ProfileScreen

@Composable
fun HomeNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeScreenRoutes.Home.route
    ) {
        composable(route = HomeScreenRoutes.Home.route) {
            ContentHomeScreen()
        }
        composable(route = HomeScreenRoutes.Cart.route) {
            CartScreen()
        }
        composable(route = HomeScreenRoutes.Profile.route) {
            ProfileScreen()
        }
    }
}

sealed class HomeScreenRoutes(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : HomeScreenRoutes(
        route = "home",
        title = "início",
        icon = Icons.Default.Home
    )

    object Cart : HomeScreenRoutes(
        route = "cart",
        title = "Carrinho",
        icon = Icons.Default.ShoppingCart
    )

    object Profile : HomeScreenRoutes(
        "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}