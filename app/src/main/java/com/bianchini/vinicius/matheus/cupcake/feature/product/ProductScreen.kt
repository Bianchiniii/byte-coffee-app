package com.bianchini.vinicius.matheus.cupcake.feature.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.cupcake.R
import com.bianchini.vinicius.matheus.cupcake.feature.home.domain.Food
import com.bianchini.vinicius.matheus.cupcake.ui.components.CardProductDetail
import com.bianchini.vinicius.matheus.cupcake.util.GlideImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    food: Food
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = { Text(food.name) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.White,
                    ),
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null) {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.util_navigate_back)
                                )
                            }
                        }
                    },
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            content = {
                GlideImageLoader(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            PaddingValues(bottom = it.calculateBottomPadding())
                        )
                        .weight(1f),
                    contentScale = ContentScale.FillBounds,
                    path = "",
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    CardProductDetail(
                        modifier = Modifier.fillMaxSize(),
                        food = food,
                    )
                }
            }
        )
    }
}