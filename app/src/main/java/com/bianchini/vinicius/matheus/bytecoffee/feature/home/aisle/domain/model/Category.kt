package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: String,
    val name: String,
    val icon: ImageVector = Icons.Filled.Coffee,
)