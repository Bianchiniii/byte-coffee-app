package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.data.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.Ticket
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository.TicketRepository
import com.bianchini.vinicius.matheus.bytecoffee.services.AisleService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val service: AisleService
) : TicketRepository {

    private var _currentTicket: MutableStateFlow<Ticket> = MutableStateFlow(
        Ticket(
            products = mutableListOf(),
            isActive = false
        )
    )

    override val currentTicket: StateFlow<Ticket> get() = _currentTicket.asStateFlow()

    override val ticket: Ticket get() = currentTicket.value

    override fun initTicket() {
        _currentTicket.update { it.copy(isActive = true) }
    }

    override fun onAddItem(newTicketItem: TicketItem) {
        _currentTicket.value.products.find { ticketItem ->
            ticketItem.product.id == newTicketItem.product.id
        }.let { product ->
            product?.copy(quantity = newTicketItem.quantity) ?: ticket.products.add(newTicketItem)
        }
    }


    override fun getTicketItem(): List<TicketItem> = ticket.products

    override fun removeTicketItem(ticketItemId: String) {
        _currentTicket.update {
            it.copy(
                products = it.products.filter { ticketItem ->
                    ticketItem.product.id != ticketItemId
                }.toMutableList()
            )
        }
    }

    override fun onTicketItemQuantityChanged(
        ticketItemId: String,
        quantity: Int
    ) {
        _currentTicket.update {
            it.copy(
                products = it.products.map { ticketItem ->
                    if (ticketItem.product.id == ticketItemId) {
                        ticketItem.copy(quantity = quantity)
                    } else {
                        ticketItem
                    }
                }.toMutableList()
            )
        }
    }

    override fun getTicketTotal(): Double = ticket.total.invoke()

    override fun finishOrder() {
        runCatching {
            val request = service.finishOrder(ticket)

            request.execute()
        }.onFailure {

        }
    }
}