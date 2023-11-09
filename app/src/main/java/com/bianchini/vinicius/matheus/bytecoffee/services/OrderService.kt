package com.bianchini.vinicius.matheus.bytecoffee.services

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.products.domain.model.TicketOrderProducts
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.data.response.TicketOrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface OrderService {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("ticket/create")
    fun finishOrder(@Body ticketOrderProducts: TicketOrderProducts): Call<TicketOrderResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("ticket/orders")
    // TODO: mudar o retorno do objeto
    fun getMyOrders(): Call<List<Boolean>?>
}