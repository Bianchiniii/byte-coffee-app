package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product

data class Ticket(
    val products: MutableList<TicketItem>,
    var total: Double,
    val isLiveOrder: Boolean = true,
)

// TODO: verificar questão da quantidade
data class TicketItem(
    val product: Product,
    var quantity: Int = 0,
)

