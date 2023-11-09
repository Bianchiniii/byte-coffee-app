package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.data.repository

import android.util.Log
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.PaymentMethod
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.products.domain.model.OrderProducts
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.products.domain.model.TicketOrderProducts
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.Ticket
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository.TicketRepository
import com.bianchini.vinicius.matheus.bytecoffee.services.AisleService
import com.bianchini.vinicius.matheus.bytecoffee.services.OrderService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val orderService: OrderService,
) : TicketRepository {

    private val profile: MutableStateFlow<Profile?> = MutableStateFlow(null)

    init {
        getProfile()
    }

    private fun getProfile() {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            profile.value = profileLocalDataSource.getProfile().getOrNull()
        }
    }

    private var _currentTicket: MutableStateFlow<Ticket> = MutableStateFlow(
        Ticket(
            products = mutableListOf(),
            paymentMethod = null,
            deliveryType = null,
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

    override fun onTicketItemQuantityIncreased(ticketItemId: String) {
        _currentTicket.update {
            it.copy(
                products = it.products.map { ticketItem ->
                    if (ticketItem.product.id == ticketItemId) {
                        ticketItem.copy(quantity = ticketItem.quantity + 1)
                    } else {
                        ticketItem
                    }
                }.toMutableList()
            )
        }
    }

    override fun onTicketItemRemoved(ticketItemId: String) {
        _currentTicket.update {
            it.copy(
                products = it.products.map { ticketItem ->
                    if (ticketItem.product.id == ticketItemId) {
                        if (ticketItem.quantity > 1) {
                            ticketItem.copy(quantity = ticketItem.quantity - 1)
                        } else ticketItem
                    } else ticketItem
                }.toMutableList()
            )
        }
    }

    override fun setPaymentMethod(paymentMethod: PaymentMethod) {
        _currentTicket.update {
            it.copy(paymentMethod = paymentMethod)
        }
    }

    override fun setDeliveryType(deliveryType: DeliveryType) {
        _currentTicket.update {
            it.copy(deliveryType = deliveryType)
        }
    }

    override fun getTicketTotal(): Double = ticket.total.invoke()

    override suspend fun finishOrder() = withContext(Dispatchers.IO) {
        val ticketOrderProducts = TicketOrderProducts(
            profileId = profile.value?.id ?: "1",
            totalInCents = ticket.total().toInt(),
            orderProducts = ticket.products.map {
                OrderProducts(
                    productId = it.product.id,
                    quantity = it.quantity
                )
            },
            deliveryType = ticket.deliveryType?.value ?: "",
            paymentMethod = ticket.paymentMethod?.name ?: ""
        )

        val request = async {
            orderService.finishOrder(ticketOrderProducts).execute()
        }.await()

        if (request.isSuccessful) {
            Resource.Result.Success(true)
        } else {
            Resource.Result.Failure(Throwable("Erro ao finalizar pedido"))
        }
    }
}