package com.bianchini.vinicius.matheus.bytecoffee.feature.home.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation.HomeViewModel
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background
import com.bianchini.vinicius.matheus.bytecoffee.util.GlideImageLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    productId: String,
) {
    val product: Product = homeViewModel.findProductById(productId).takeIf {
        it != null
    } ?: run {
        navController.popBackStack()

        return
    }

    var itemQuantity by remember { mutableIntStateOf(1) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.util_navigate_back)) },
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .verticalScroll(rememberScrollState()),
        ) {
            GlideImageLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentScale = ContentScale.FillBounds,
                path = product.image,
            )

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                ProductDetail(
                    product = product,
                    quantity = itemQuantity,
                    onInciseItem = {
                        itemQuantity += 1
                    },
                    onRemoveItem = {
                        if (itemQuantity > 1) {
                            itemQuantity -= 1
                        }
                    },
                )

                Spacer(modifier = Modifier.height(24.dp))

                ButtonPrimary(
                    onClick = {
                        homeViewModel.addProduct(
                            quantity = itemQuantity,
                            product = product
                        )

                        navController.navigateUp()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    value = stringResource(R.string.product_details_add_item)
                )
            }
        }
    }
}

@Composable
fun ProductDetail(
    product: Product,
    quantity: Int? = 0,
    onInciseItem: () -> Unit,
    onRemoveItem: () -> Unit,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            NormalText(
                value = product.name,
                modifier = Modifier.wrapContentWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20,
            )

            NormalText(
                value = "R$: ${product.price}",
                modifier = Modifier.wrapContentWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 20
            )
        }

        SetupDivider()

        NormalText(
            value = product.description,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Start),
            textAlign = androidx.compose.ui.text.style.TextAlign.Justify,
        )

        SetupDivider()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            NormalText(
                value = stringResource(id = R.string.product_quantity),
                modifier = Modifier
            )
            IconButton(onClick = { onRemoveItem.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.Remove,
                    contentDescription = stringResource(R.string.product_add_item)
                )
            }
            NormalText(
                value = quantity.toString(),
                modifier = Modifier
            )
            IconButton(onClick = { onInciseItem.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.product_remove_item)
                )
            }
        }
    }
}

@Composable
fun SetupDivider() {
    Divider(
        modifier = Modifier.padding(
            vertical = 24.dp
        ),
        color = Color.LightGray
    )
}

@Preview(showBackground = true)
@Composable
fun FoodSizeItem() {
    ProductDetail(
        product = Product(
            id = "1",
            name = "item",
            description = "size.description",
            price = 10.0,
            image = "",
            categoryId = "1234"
        ),
        quantity = 1,
        onInciseItem = {},
        onRemoveItem = {},
    )
}
