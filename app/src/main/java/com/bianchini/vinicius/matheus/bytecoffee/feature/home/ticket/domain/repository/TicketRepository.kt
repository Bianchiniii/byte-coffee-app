package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.Ticket
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import kotlinx.coroutines.flow.StateFlow

interface TicketRepository {

    val currentTicket: StateFlow<Ticket>

    val ticket: Ticket

    fun initTicket()

    fun onAddItem(ticketItem: TicketItem)

    fun getTicketItem(): List<TicketItem>

    fun removeTicketItem(ticketItemId: String)

    fun onTicketItemQuantityChanged(
        ticketItemId: String,
        quantity: Int
    )

    fun getTicketTotal(): Double

    fun finishOrder()
}