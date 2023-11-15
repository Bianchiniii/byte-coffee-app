package com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.AllOrders
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.OrderProduct
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.orders.domain.model.TicketOrder
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    navHostController: NavHostController,
    modifier: Modifier,
    orders: AllOrders?,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.util_navigate_back)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.White,
                ),
                navigationIcon = {
                    if (navHostController.previousBackStackEntry != null) {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.util_navigate_back)
                            )
                        }
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            AnimatedVisibility(visible = orders == null) {
                EmptyOrders()
            }

            AnimatedVisibility(visible = orders != null) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    NormalText(
                        value = stringResource(id = R.string.order_list_description),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OrdersList(orders?.tickets ?: emptyList())
                }
            }
        }
    }
}

@Composable
fun OrdersList(ticketOrders: List<TicketOrder>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(items = ticketOrders) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    NormalText(
                        value = it.createdAt,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val products = it.orderProducts.joinToString(separator = ", ") { orderProduct ->
                        "${orderProduct.productName} x ${orderProduct.quantity}"
                    }

                    NormalText(
                        value = products,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        fontSize = 16
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        NormalText(
                            value = it.status,
                            modifier = Modifier,
                            fontSize = 16
                        )
                        NormalText(
                            value = "RS: ${it.total}",
                            modifier = Modifier,
                            fontSize = 16
                        )
                    }
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(2.dp)
            )
        }
    }
}

@Composable
fun EmptyOrders() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painter = painterResource(R.drawable.empty_cart), contentDescription = "")
        NormalText(
            value = stringResource(id = R.string.orders_empty_title),
            modifier = Modifier.wrapContentSize(),
            fontWeight = FontWeight.Bold,
            fontSize = 16,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        NormalText(
            value = stringResource(id = R.string.orders_empty_description),
            modifier = Modifier.wrapContentSize(),
            fontSize = 16,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Preview
@Composable
fun PreviewOrdersScreen() {
    OrdersScreen(
        navHostController = NavHostController(LocalContext.current),
        modifier = Modifier,
        orders = AllOrders(
            tickets = listOf(
                TicketOrder(
                    id = "1",
                    createdAt = "2021-09-01",
                    status = "PENDING",
                    total = 10,
                    orderProducts = listOf(
                        OrderProduct(
                            productId = "1",
                            productName = "Café",
                            quantity = 1,
                        )
                    )
                ),
                TicketOrder(
                    id = "1",
                    createdAt = "2021-09-01",
                    status = "PENDING",
                    total = 10,
                    orderProducts = listOf(
                        OrderProduct(
                            productId = "1",
                            productName = "Café",
                            quantity = 1,
                        )
                    )
                ),
                TicketOrder(
                    id = "1",
                    createdAt = "2021-09-01",
                    status = "PENDING",
                    total = 10,
                    orderProducts = listOf(
                        OrderProduct(
                            productId = "1",
                            productName = "Café",
                            quantity = 1,
                        )
                    )
                ),
                TicketOrder(
                    id = "1",
                    createdAt = "2021-09-01",
                    status = "PENDING",
                    total = 10,
                    orderProducts = listOf(
                        OrderProduct(
                            productId = "1",
                            productName = "Café",
                            quantity = 1,
                        )
                    )
                ),
            )
        )
    )
}
