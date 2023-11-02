package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.data.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.Ticket
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository.TicketRepository
import com.bianchini.vinicius.matheus.bytecoffee.services.AisleService
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val service: AisleService
) : TicketRepository {

    override lateinit var currentTicket: Ticket

    override fun initTicket(): Ticket {
        currentTicket = Ticket(
            products = mutableListOf(),
            total = 0.0
        )

        return currentTicket
    }

    override fun onAddItem(ticketItem: TicketItem) {
        currentTicket.products.find {
            it.product.id == ticketItem.product.id
        }?.let {
            it.quantity += ticketItem.quantity
        }.run {
            currentTicket.products.add(ticketItem)
        }

        refreshTotal()
    }


    private fun refreshTotal() {
        val products = currentTicket.products
        var total = 0.0

        products.forEach {
            total += it.product.price * it.quantity
        }

        currentTicket.total = total
    }

    override fun getTicketItem(): List<TicketItem> = currentTicket.products

    override fun onTicketItemChanged(
        ticketItemId: String,
        quantity: Int
    ) {
        currentTicket.products.find {
            it.product.id == ticketItemId
        }?.let {
            it.quantity = quantity
        }
    }

    override fun getTicketTotal(): Double {
        return currentTicket.total
    }

    override fun finishOrder() {
        runCatching {
            val request = service.finishOrder(currentTicket)

            request.execute()
        }.onFailure {

        }
    }
}