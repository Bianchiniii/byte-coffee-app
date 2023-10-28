package com.bianchini.vinicius.matheus.bytecoffee.feature.home.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Food
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background
import com.bianchini.vinicius.matheus.bytecoffee.util.GlideImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    food: Food
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = it),
            content = {
                GlideImageLoader(
                    modifier = Modifier
                        .fillMaxSize()
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

@Composable
fun CardProductDetail(
    modifier: Modifier,
    food: Food,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(Color.Cyan),
        shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ListFoodSize(food.name, food.size)
            Spacer(modifier = Modifier.padding(12.dp))
            NormalText(
                value = stringResource(R.string.product_details_about),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            NormalText(
                value = food.description,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Start)
            )
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                ButtonPrimary(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    value = stringResource(R.string.product_details_add_item)
                )
            }
        }
    }
}

@Composable
fun ListFoodSize(
    foodName: String,
    sizes: List<Food.Size>
) {
    NormalText(
        value = stringResource(
            id = R.string.product_details_select_size,
            formatArgs = arrayOf(foodName)
        ),
        fontSize = 24,
        modifier = Modifier.wrapContentSize()
    )
    Spacer(modifier = Modifier.padding(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        LazyRow {
            items(items = sizes) { size ->
                FoodSizeItem(size)
            }
        }
    }
}

@Composable
fun FoodSizeItem(size: Food.Size) {
    ButtonPrimary(
        value = size.label,
        onClick = { },
        modifier = Modifier.wrapContentWidth()
    )
}