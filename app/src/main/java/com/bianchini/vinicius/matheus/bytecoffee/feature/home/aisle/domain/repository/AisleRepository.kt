package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product

interface AisleRepository {

    suspend fun getAisleProducts(): Resource.Result<List<Product>?, Throwable>

    suspend fun getAisleCategories(): Resource.Result<List<Category>?,Throwable>
}