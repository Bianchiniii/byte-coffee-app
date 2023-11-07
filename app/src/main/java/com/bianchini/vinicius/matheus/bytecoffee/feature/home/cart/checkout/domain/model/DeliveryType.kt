package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model

enum class DeliveryType(
    val type: Int,
    val value: String
) {
    PICKUP(type = 0, value = "Retirada"),
    DELIVERY(type = 1, value = "Entrega")
}