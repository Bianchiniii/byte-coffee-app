package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.model

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product

data class Ticket(
    val products: MutableList<TicketItem> = mutableListOf(),
    val total: Double = 0.0
)

// TODO: ver se vai ficar var
data class TicketItem(
    val product: Product,
    var quantity: Int = 0,
)

