package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
//    val size: List<Size>,
    val price: Double,
    val image: String,
    val categoryId :String
) {

    /*   enum class Size(
           val value: Int,
           val label: String
       ) {
           SMALL(0, "Pequeno"),
           MEDIUM(1, "Médio"),
           LARGE(2, "Grande")
       }*/
}