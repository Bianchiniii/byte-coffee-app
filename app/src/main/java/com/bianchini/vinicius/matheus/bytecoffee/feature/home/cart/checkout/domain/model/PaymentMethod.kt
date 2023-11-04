package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class PaymentMethod(
    val id: String,
    val name: String,
    val icon: ImageVector
)
