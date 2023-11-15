package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun OrdersRoute(
    modifier: Modifier = Modifier,
    ordersViewModel: OrdersViewModel,
    navHostController: NavHostController,
    userId: String
) {
    ordersViewModel.getOrders(userId)

    val orders by ordersViewModel.allOrdersState.collectAsStateWithLifecycle()

    OrdersScreen(
        modifier = modifier,
        navHostController = navHostController,
        orders = orders
    )
}