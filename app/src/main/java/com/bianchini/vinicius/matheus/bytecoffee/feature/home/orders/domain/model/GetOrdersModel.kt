package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model

import com.google.gson.annotations.SerializedName

data class GetOrdersModel(
    @SerializedName("profile_id")
    val profileId: String
)