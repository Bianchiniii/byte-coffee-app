package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.AllOrders
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.GetOrdersModel

interface OrdersRepository {

    suspend fun getOrders(orderModel: GetOrdersModel): Resource.Result<AllOrders, Throwable>
}