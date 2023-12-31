package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.data.response

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price_in_cents")
    val price: Double,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("image")
    val image: String
)

fun List<ProductsResponse>.toDomain() = map {
    Product(
        id = it.id,
        name = it.name,
        description = it.description,
        price = it.price,
        // TODO: mapear categoria qnd implementado
        categoryId = "",
        image = it.image
    )
}
