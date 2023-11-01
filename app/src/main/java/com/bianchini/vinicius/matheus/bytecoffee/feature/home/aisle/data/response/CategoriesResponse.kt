package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.data.response

import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

fun List<CategoriesResponse>.toDomain() = map {
    Category(
        id = it.id,
        name = it.name
    )
}
