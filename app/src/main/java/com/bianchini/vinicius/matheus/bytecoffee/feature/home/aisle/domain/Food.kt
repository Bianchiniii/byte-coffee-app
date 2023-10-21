package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain

data class Food(
    val id: Int,
    val name: String,
    val description: String,
    val size: List<Size>,
    val prince: Double,
    val image: String
) {

    enum class Size(
        val value: Int,
        val label: String
    ) {
        SMALL(0, "Pequeno"),
        MEDIUM(1, "Médio"),
        LARGE(2, "Grande")
    }
}