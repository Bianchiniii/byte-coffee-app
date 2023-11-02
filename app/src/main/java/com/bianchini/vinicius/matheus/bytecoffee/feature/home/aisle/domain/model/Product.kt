package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val categoryId: String
)