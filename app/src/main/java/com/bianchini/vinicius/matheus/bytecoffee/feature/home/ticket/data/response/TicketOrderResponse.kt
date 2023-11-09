package com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.data.response

import com.google.gson.annotations.SerializedName

data class TicketOrderResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
)