package com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.repository.AisleRepository
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource,
//    private val profileLocalAddressDataSource: ProfileLocalAddressDataSource,
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
            street = "Rua dos jericó",
            number = "777",
            neighborhood = "Bairro 1",
            cityAndState = "Cidade 1",
        )
    )
    val storeAddress = _storeAddress.asStateFlow()

    private val _paymentMethods = MutableStateFlow(
        listOf(
            PaymentMethod(
                id = "1",
                name = "Dinheiro",
                icon = Icons.Outlined.Money
            ),
            PaymentMethod(
                id = "1",
                name = "Cartão",
                icon = Icons.Outlined.CreditCard
            )
        )
    )
    val paymentMethods = _paymentMethods.asStateFlow()

    init {
        getUser()
        getAddress()

        // TODO: sera implementado futuramente
//        getCategories()

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
//            val localAddress = profileLocalAddressDataSource.findAddress()
//            _userAddress.value = localAddress.getOrNull()
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            val request = aisleRepository.getAisleProducts()

            _products.value = request.getOrNull()
        }
    }

    // TODO: sera implementado futuramente
    /*private fun getCategories() {
        viewModelScope.launch {
            val request = aisleRepository.getAisleCategories()

            _categories.value = request.getOrNull()
        }
    }*/

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

    fun removeTicketItem(ticketItemId: String) {
        ticketRepository.onTicketItemRemoved(ticketItemId)
    }

    fun getTicketTotal(): Double {
        return ticketRepository.getTicketTotal()
    }

    fun finishOrder() {
        ticketRepository.finishOrder()
    }
}