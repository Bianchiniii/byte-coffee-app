package com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation

import Resource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.repository.AisleRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.PaymentMethod
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Address
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.address.ProfileLocalAddressDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import extension.getOrNull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val profileLocalAddressDataSource: ProfileLocalAddressDataSource,
    private val aisleRepository: AisleRepository,
    private val ticketRepository: TicketRepository
) : ViewModel() {

    private var _userProfile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val userProfile = _userProfile.asStateFlow()

    private var _userAddress: MutableStateFlow<Address?> = MutableStateFlow(null)
    val userAddress = _userAddress.asStateFlow()

    private var _categories: MutableStateFlow<List<Category>?> = MutableStateFlow(emptyList())
    val categories = _categories.asStateFlow()

    private var _products: MutableStateFlow<List<Product>?> = MutableStateFlow(emptyList())
    val products = _products.asStateFlow()

    val ticketState = ticketRepository.currentTicket

    private val _storeAddress = MutableStateFlow(
        Address(
            id = "1",
            street = "Rua Navegantes",
            number = "463",
            neighborhood = "Bairro Alto da XV",
            cityAndState = "Curitiba - PR",
        )
    )
    val storeAddress = _storeAddress.asStateFlow()

    private val _paymentMethods = MutableStateFlow(
        listOf(
            PaymentMethod(
                id = "1",
                name = "Dinheiro",
                icon = Icons.Outlined.Money,
                tint = Green
            ),
            PaymentMethod(
                id = "1",
                name = "Cartão",
                icon = Icons.Outlined.CreditCard,
                tint = Black
            )
        )
    )
    val paymentMethods = _paymentMethods.asStateFlow()

    private val _deliveryTypesList = MutableStateFlow(
        listOf(
            DeliveryType.PICKUP,
            DeliveryType.DELIVERY
        )
    )
    val deliveryTypesList = _deliveryTypesList.asStateFlow()

    private val _showBottomSheetOrderFinished = MutableStateFlow(false)
    val showBottomSheetOrderFinished = _showBottomSheetOrderFinished.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        getUser()
        getAddress()

        getProducts()

        ticketRepository.initTicket()
    }

    private fun getUser() {
        viewModelScope.launch {
            val request = profileLocalDataSource.getProfile()

            _userProfile.value = request.getOrNull()
        }
    }

    private fun getAddress() {
        viewModelScope.launch {
            val localAddress = profileLocalAddressDataSource.findAddress()

            _userAddress.value = localAddress.getOrNull()
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            val request = aisleRepository.getAisleProducts()

            _products.value = request.getOrNull()
        }
    }

    fun findProductById(productId: String): Product? {
        return _products.value?.find { it.id == productId }
    }

    fun addProduct(quantity: Int, product: Product) {
        ticketRepository.onAddItem(
            ticketItem = TicketItem(
                product = product,
                quantity = quantity
            )
        )
    }

    fun increaseTicketItemQuantity(ticketItemId: String) {
        ticketRepository.onTicketItemQuantityIncreased(ticketItemId)
    }

    fun removeTicketItem(ticketItemId: String, shouldRemoveFromCart: Boolean) {
        ticketRepository.onTicketItemRemoved(ticketItemId, shouldRemoveFromCart)
    }

    fun getTicketTotal(): Double {
        return ticketRepository.getTicketTotal()
    }

    fun finishOrder() {
        _loading.value = true

        viewModelScope.launch {
            val request = ticketRepository.finishOrder()

            when (request) {
                is Resource.Result.Failure -> {
                    _loading.value = false

                    updateShouldShowBottomSheetOrderFinished(false)
                }

                is Resource.Result.Success -> {
                    _loading.value = false

                    updateShouldShowBottomSheetOrderFinished(true)
                }
            }
        }
    }

    fun updateShouldShowBottomSheetOrderFinished(shouldShow: Boolean) {
        _showBottomSheetOrderFinished.value = shouldShow
    }

    fun onSelectedPaymentMethod(paymentMethod: PaymentMethod) {
        ticketRepository.setPaymentMethod(paymentMethod)
    }

    fun onSelectedDeliveryType(deliveryType: DeliveryType) {
        ticketRepository.setDeliveryType(deliveryType)
    }
}