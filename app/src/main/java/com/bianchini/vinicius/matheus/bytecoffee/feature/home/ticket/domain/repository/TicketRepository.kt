package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.Ticket
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem

interface TicketRepository {

    var currentTicket: Ticket

    fun initTicket(): Ticket

    fun onAddItem(ticketItem: TicketItem)

    fun getTicketItem(): List<TicketItem>

    fun onTicketItemChanged(
        ticketItemId: String,
        quantity: Int
    )

    fun getTicketTotal(): Double

    fun finishOrder()
}