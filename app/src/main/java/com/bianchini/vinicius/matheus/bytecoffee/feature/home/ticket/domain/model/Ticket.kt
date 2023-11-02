package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product

data class Ticket(
    val products: MutableList<TicketItem>,
    val isActive: Boolean,
) {

    val total = {
        val products = products
        var total = 0.0

        products.forEach {
            total += it.product.price * it.quantity
        }

        total
    }
}

data class TicketItem(
    val product: Product,
    val quantity: Int,
)

