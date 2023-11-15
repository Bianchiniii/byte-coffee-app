package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model

data class AllOrders(
    val tickets: List<TicketOrder>
)

data class TicketOrder(
    val createdAt: String,
    val id: String,
    val orderProducts: List<OrderProduct>,
    val status: String,
    val total: Int
)

data class OrderProduct(
    val productId: String,
    val productName: String,
    val quantity: Int
)