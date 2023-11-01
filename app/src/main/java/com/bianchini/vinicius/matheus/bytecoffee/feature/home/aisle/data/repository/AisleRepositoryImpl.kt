package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.data.repository

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.data.response.toDomain
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.repository.AisleRepository
import com.bianchini.vinicius.matheus.bytecoffee.services.AisleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AisleRepositoryImpl @Inject constructor(
    private val service: AisleService
) : AisleRepository {
    override suspend fun getAisleProducts() = withContext(Dispatchers.IO) {
        val request = async {
            service.getProducts().execute()
        }.await()

        if (request.isSuccessful) {
            val listProducts = request.body()?.toDomain()

            return@withContext Resource.Result.Success(listProducts)
        } else {
            return@withContext Resource.Result.Failure(Throwable("Failed to get products!"))
        }
    }

    override suspend fun getAisleCategories() = withContext(Dispatchers.IO) {
        val request = async {
            service.getCategories().execute()
        }.await()

        if (request.isSuccessful) {
            val listCategories = request.body()?.toDomain()

            return@withContext Resource.Result.Success(listCategories)
        } else {
            return@withContext Resource.Result.Failure(Throwable("Failed to get categories!"))
        }
    }
}