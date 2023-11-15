package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.data.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.data.response.toAllOrders
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.AllOrders
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.GetOrdersModel
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.repository.OrdersRepository
import com.bianchini.vinicius.matheus.bytecoffee.services.OrderService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    private val ordersService: OrderService
) : OrdersRepository {

    override suspend fun getOrders(
        orderModel: GetOrdersModel
    ): Resource.Result<AllOrders, Throwable> = withContext(Dispatchers.IO) {
        val request = async {
            ordersService.getMyOrders(orderModel).execute()
        }.await()

        if (request.isSuccessful) {
            Resource.Result.Success(request.body().toAllOrders())
        } else {
            Resource.Result.Failure(Throwable("Erro ao buscar pedidos"))
        }
    }
}