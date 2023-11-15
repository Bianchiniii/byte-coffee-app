package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.data.response

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.AllOrders
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.OrderProduct
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.TicketOrder

data class OrdersResponse(
    val tickets: List<TicketResponse>
)

data class TicketResponse(
    val createdAt: String,
    val id: String,
    val orderProducts: List<OrderProductResponse>,
    val status: String,
    val total: Int
)

data class OrderProductResponse(
    val productId: String,
    val productName: String,
    val quantity: Int
)

fun OrdersResponse.toAllOrders() = AllOrders(
    tickets = tickets.map {
        TicketOrder(
            id = it.id,
            createdAt = it.createdAt,
            orderProducts = it.orderProducts.map {
                OrderProduct(
                    productId = it.productId,
                    productName = it.productName,
                    quantity = it.quantity
                )
            },
            status = it.status,
            total = it.total
        )
    }
)