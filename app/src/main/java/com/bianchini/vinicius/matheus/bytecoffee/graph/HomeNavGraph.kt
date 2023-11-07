package com.bianchini.vinicius.matheus.bytecoffee.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.presentation.ContentHomeScreen
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.presentation.CartCheckoutScreen
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.products.presentation.CartScreen
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation.HomeViewModel
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.presentation.OrdersScreen
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.product.ProductScreen
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.presentation.ProfileScreen
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.presentation.ProfileViewModel

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreenRoutes.Home.route
    ) {
        composable(route = HomeScreenRoutes.Home.route) {
            ContentHomeScreen(
                navController = navController,
                paddingValues,
                viewModel
            )
        }
        cartNavGraph(navController = navController, viewModel, paddingValues)
        composable(route = HomeScreenRoutes.Profile.route) {
            val profileViewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(paddingValues, profileViewModel)
        }
        ordersNavGraph(navController = navController, viewModel)
        detailsNavGraph(navController = navController, viewModel)
    }
}

fun NavGraphBuilder.cartNavGraph(
    navController: NavHostController,
    viewModel: HomeViewModel,
    paddingValues: PaddingValues
) {
    navigation(
        route = Graph.CART,
        startDestination = HomeScreenRoutes.Cart.route
    ) {
        composable(route = HomeScreenRoutes.Cart.route) {
            CartScreen(
                paddingValues,
                viewModel,
                navController
            )
        }
        composable(route = CartScreenRoutes.CartCheckout.route) {
            CartCheckoutScreen(
                navController = navController,
                homeViewModel = viewModel
            )
        }
    }
}

sealed class CartScreenRoutes(val route: String) {
    object CartCheckout : CartScreenRoutes(route = "checkout")
}

fun NavGraphBuilder.ordersNavGraph(navController: NavHostController, viewModel: HomeViewModel) {
    navigation(
        route = Graph.ORDERS,
        startDestination = OrdersScreenRoutes.Orders.route
    ) {
        composable(route = OrdersScreenRoutes.Orders.route) {
            OrdersScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

sealed class OrdersScreenRoutes(val route: String) {
    object Orders : OrdersScreenRoutes(route = "orders")
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController, viewModel: HomeViewModel) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreenRoutes.ProductScreen.route
    ) {
        composable(
            route = DetailsScreenRoutes.ProductScreen.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductScreen(
                navController = navController,
                productId = productId!!,
                homeViewModel = viewModel
            )
        }
    }
}

sealed class DetailsScreenRoutes(val route: String) {
    object ProductScreen : DetailsScreenRoutes(route = "product/{productId}")
}

sealed class HomeScreenRoutes(
    val route: String,
    val title: String,
    val icon: ImageVector,
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
        route = "profile/{padding}",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}