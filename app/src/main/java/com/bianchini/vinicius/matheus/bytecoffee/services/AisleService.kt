package com.bianchini.vinicius.matheus.bytecoffee.services

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.data.response.CategoriesResponse
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.data.response.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface AisleService {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("products/all")
    fun getProducts(): Call<List<ProductsResponse>?>


    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("products/categories")
    fun getCategories(): Call<List<CategoriesResponse>?>
}