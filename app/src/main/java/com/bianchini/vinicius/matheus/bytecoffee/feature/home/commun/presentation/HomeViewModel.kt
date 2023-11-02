package com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.repository.AisleRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
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
    private val aisleRepository: AisleRepository,
    private val ticketRepository: TicketRepository
) : ViewModel() {

    private var _userProfile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val userProfile = _userProfile.asStateFlow()

    private var _categories: MutableStateFlow<List<Category>?> = MutableStateFlow(emptyList())
    val categories = _categories.asStateFlow()

    private var _products: MutableStateFlow<List<Product>?> = MutableStateFlow(emptyList())
    val products = _products.asStateFlow()

    init {
        getUser()

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

    fun getTicketItems(): List<TicketItem> = ticketRepository.getTicketItem()

    fun finishOrder() {
        ticketRepository.finishOrder()
    }
}