package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model

import com.bianchini.vinicius.matheus.bytecoffee.db.address.entity.AddressEntity

data class Address(
    val id: String,
    val street: String,
    val number: String,
    val neighborhood: String,
    val cityAndState: String,
)

fun Address.toEntity() = AddressEntity(
    id = id,
    street = street,
    number = number,
    neighborhood = neighborhood,
    cityAndState = cityAndState,
)
