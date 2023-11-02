package com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.repository.AisleRepository
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.Profile
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.repository.profile.ProfileLocalDataSource
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.model.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import extension.getOrNull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val aisleRepository: AisleRepository
) : ViewModel() {

    private var _userProfile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val userProfile = _userProfile.asStateFlow()

    private var _categories: MutableStateFlow<List<Category>?> = MutableStateFlow(emptyList())
    val categories = _categories.asStateFlow()

    private var _products: MutableStateFlow<List<Product>?> = MutableStateFlow(emptyList())
    val products = _products.asStateFlow()

    private val _currentTicket = MutableStateFlow(Ticket())
    val currentTicket = _currentTicket.asStateFlow()

    init {
        getUser()
        getCategories()
        getProducts()
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

    private fun getCategories() {
        viewModelScope.launch {
            val request = aisleRepository.getAisleCategories()

            _categories.value = request.getOrNull()
        }
    }

    fun findProductById(productId: String): Product? {
        return _products.value?.find { it.id == productId }
    }

    fun addProduct(quantity: Int, product: Product) {
        // TODO: add item no ticket
        /*_currentTicket.update {
            it.products.add(product)
        }*/
    }

    fun confirmOrder() {
        TODO("Not yet implemented")
    }
}