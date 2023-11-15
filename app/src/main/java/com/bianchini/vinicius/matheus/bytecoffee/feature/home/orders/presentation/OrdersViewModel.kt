package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.AllOrders
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.GetOrdersModel
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.repository.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import extension.getOrNull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersRepository: OrdersRepository,
) : ViewModel() {

    private val allOrders = MutableStateFlow<AllOrders?>(null)
    val allOrdersState = allOrders.asStateFlow()

    fun getOrders(
        userId: String
    ) {
        viewModelScope.launch {
            val request = ordersRepository.getOrders(
                orderModel = GetOrdersModel(
                    profileId = userId
                )
            )

            allOrders.value = request.getOrNull()
        }
    }
}