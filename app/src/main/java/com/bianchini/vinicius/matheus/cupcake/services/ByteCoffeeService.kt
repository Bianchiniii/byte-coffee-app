package com.bianchini.vinicius.matheus.cupcake.services

import retrofit2.http.GET
import retrofit2.http.POST

interface ByteCoffeeService {

    @POST
    suspend fun login()

    @POST
    suspend fun register()

    @GET
    suspend fun getProducts()
}