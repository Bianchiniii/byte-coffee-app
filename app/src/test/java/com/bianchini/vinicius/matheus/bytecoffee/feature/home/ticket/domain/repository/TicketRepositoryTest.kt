package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Money
import androidx.compose.ui.graphics.Color
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.PaymentMethod
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.data.repository.TicketRepositoryImpl
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TicketRepositoryTest {

    private lateinit var ticketRepository: TicketRepository

    @Before
    fun setUp() {
        ticketRepository = TicketRepositoryImpl(
            profileLocalDataSource = mockk(),
            orderService = mockk()
        )
    }

    @Test
    fun `should init ticket`() {
        // Act
        ticketRepository.initTicket()

        // Assert
        assert(ticketRepository.ticket.isActive)
    }

    @Test
    fun `should add item to ticket`() {
        // Act
        ticketRepository.onAddItem(ticketItem = ticketItem)

        // Assert
        assert(ticketRepository.ticket.products.find { it.product.id == productId } != null)
    }

    @Test
    fun `should get ticket item`() {
        // Act
        ticketRepository.onAddItem(ticketItem = ticketItem)

        // Assert
        assert(ticketRepository.getTicketItem().isNotEmpty())
    }

    @Test
    fun `should remove ticket item`() {
        // Arrange
        ticketRepository.onAddItem(ticketItem = ticketItem)

        // Act
        ticketRepository.removeTicketItem(ticketItemId = ticketItem.product.id)

        // Assert
        assert(ticketRepository.getTicketItem().isEmpty())
    }

    @Test
    fun `should increase ticket item quantity`() {
        // Arrange
        ticketRepository.onAddItem(ticketItem = ticketItem)

        // Act
        ticketRepository.onTicketItemQuantityIncreased(ticketItemId = ticketItem.product.id)

        // Assert
        assert(ticketRepository.ticket.products.find { it.product.id == productId }?.quantity == 2)
    }

    @Test
    fun `should remove ticket item quantity`() {
        // Arrange
        ticketRepository.onAddItem(ticketItem = ticketItem)

        // Act
        ticketRepository.onTicketItemRemoved(
            ticketItemId = ticketItem.product.id,
            shouldRemoveFromCart = true
        )

        // Assert
        assert(ticketRepository.ticket.products.isEmpty())
    }

    @Test
    fun `should get ticket total`() {
        // Arrange
        ticketRepository.onAddItem(ticketItem = ticketItem)

        // Act
        val total = ticketRepository.getTicketTotal()

        // Assert
        assert(total == 10.0)
    }

    @Test
    fun `should set payment method`() {
        // Act
        ticketRepository.setPaymentMethod(paymentMethod = paymentMethod)

        // Assert
        assert(ticketRepository.ticket.paymentMethod?.id == paymentMethodID)
    }

    @Test
    fun `should set delivery type`() {
        // Act
        ticketRepository.setDeliveryType(deliveryType = deliveryType)

        // Assert
        assert(ticketRepository.ticket.deliveryType?.value == deliveryType.value)
    }


    companion object {
        private const val productId = "a2f2e802-887a-11ee-b9d1-0242ac120002"
        private const val categoryId = "ae173a94-887a-11ee-b9d1-0242ac120002"
        private const val paymentMethodID = "b3bc8bfc-887a-11ee-b9d1-0242ac120002"

        val paymentMethod = PaymentMethod(
            id = paymentMethodID,
            name = "Dinheiro",
            icon = Icons.Outlined.Money,
            tint = Color.Green
        )

        val deliveryType = DeliveryType.DELIVERY

        val ticketItem = TicketItem(
            product = Product(
                id = productId,
                name = "Café",
                description = "Café",
                price = 10.0,
                image = "https://www.google.com.br",
                categoryId = categoryId,
            ),
            quantity = 1,
        )
    }
}
