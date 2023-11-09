package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.products.domain.model

import com.google.gson.annotations.SerializedName

data class TicketOrderProducts(
    @SerializedName("profileId")
    val profileId: String,
    @SerializedName("totalInCents")
    val totalInCents: Int,
    @SerializedName("orderProducts")
    val orderProducts: List<OrderProducts>,
    @SerializedName("deliveryType")
    val deliveryType: String,
    @SerializedName("paymentMethod")
    val paymentMethod: String
)

data class OrderProducts(
    @SerializedName("productId")
    val productId: String,
    @SerializedName("quantity")
    val quantity: Int
)