package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.PaymentMethod
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

    fun onTicketItemQuantityIncreased(ticketItemId: String)

    fun onTicketItemRemoved(ticketItemId: String)

    fun getTicketTotal(): Double

    fun setPaymentMethod(paymentMethod: PaymentMethod)

    fun setDeliveryType(deliveryType: DeliveryType)

    suspend fun finishOrder(): Resource.Result<Boolean, Throwable?>
}