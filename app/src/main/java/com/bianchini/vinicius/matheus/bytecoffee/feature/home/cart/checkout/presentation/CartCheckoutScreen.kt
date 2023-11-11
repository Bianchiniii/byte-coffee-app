package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType.DELIVERY
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType.PICKUP
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.PaymentMethod
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation.HomeViewModel
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.confirmorder.ConfirmOrderBottomSheet
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import com.bianchini.vinicius.matheus.bytecoffee.feature.loading.LoadingScreen
import com.bianchini.vinicius.matheus.bytecoffee.graph.OrdersScreenRoutes
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.GrayNeutral

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartCheckoutScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val userAddress = homeViewModel.userAddress.collectAsStateWithLifecycle().value

    val storeAddress = homeViewModel.storeAddress.collectAsStateWithLifecycle().value

    val paymentMethods = homeViewModel.paymentMethods.collectAsStateWithLifecycle()
    val deliveryTypesList = homeViewModel.deliveryTypesList.collectAsStateWithLifecycle().value

    val ticketItems = homeViewModel.ticketState.collectAsStateWithLifecycle().value.products

    val currentDeliveryType = remember { mutableStateOf(deliveryTypesList.first()) }

    val orderFinished = homeViewModel.orderFinished.collectAsStateWithLifecycle().value

    val loading = homeViewModel.loading.collectAsStateWithLifecycle().value

    if (orderFinished) {
        ConfirmOrderBottomSheet {
            navController.navigate(OrdersScreenRoutes.Orders.route)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.cart_checkout_title)) },
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
        },
    ) {
        if (loading) {
            LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = it.calculateTopPadding(),
                        bottom = 24.dp
                    )
                    .fillMaxSize()
            ) {
                ShowSpacer()
                NormalText(
                    value = stringResource(id = R.string.cart_checkout_read_order_and_confirm),
                    modifier = Modifier
                )
                ShowSpacer()
                SelectOrderDeliveryType(
                    deliveryTypes = deliveryTypesList,
                    onSelectDeliveryType = {
                        homeViewModel.onSelectedDeliveryType(it)
                        currentDeliveryType.value = it
                    },
                    currentDeliveryType = currentDeliveryType.value
                )
                ShowSpacer()
                DeliveryTypeInfo(
                    deliveryType = currentDeliveryType.value,
                    streetAndNumber = if (currentDeliveryType.value == PICKUP) {
                        "${storeAddress.street}, ${storeAddress.number}"
                    } else "${userAddress?.street}, ${userAddress?.number}",
                    neighborhoodAndCityAndState = if (currentDeliveryType.value == PICKUP) {
                        "${storeAddress.neighborhood}, ${storeAddress.cityAndState}"
                    } else "${userAddress?.neighborhood}, ${userAddress?.cityAndState}",
                )
                ShowSpacer()
                OrderResume(
                    orderProducts = ticketItems,
                    totalOrder = homeViewModel.getTicketTotal()
                )
                ShowSpacer()
                SelectOrderPaymentType(
                    paymentMethods = paymentMethods.value,
                    onPaymentMethodSelected = { homeViewModel.onSelectedPaymentMethod(it) }
                )
                ShowSpacer()
                ConfirmOrder(confirmPayment = { homeViewModel.finishOrder() })
            }
        }
    }
}

@Composable
fun DeliveryTypeInfo(
    deliveryType: DeliveryType,
    streetAndNumber: String,
    neighborhoodAndCityAndState: String,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GrayNeutral),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val textValue = when (deliveryType) {
                PICKUP -> stringResource(id = R.string.cart_checkout_pickup_address)
                DELIVERY -> stringResource(id = R.string.cart_checkout_delivery_address)
            }
            NormalText(
                value = textValue,
                modifier = Modifier,
                fontWeight = FontWeight.Bold
            )
            ShowSpacer()
            NormalText(
                value = stringResource(
                    id = R.string.cart_address_description,
                    listOf(streetAndNumber, neighborhoodAndCityAndState)
                ),
                modifier = Modifier,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryTypeInfoPreview() {
    DeliveryTypeInfo(
        deliveryType = DELIVERY,
        streetAndNumber = "Rua das Flores, 123",
        neighborhoodAndCityAndState = "Centro, São Paulo - SP",
    )
}


@Composable
fun SelectOrderDeliveryType(
    deliveryTypes: List<DeliveryType>,
    onSelectDeliveryType: (DeliveryType) -> Unit,
    currentDeliveryType: DeliveryType
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GrayNeutral),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            deliveryTypes.forEach { text ->
                RadioButton(
                    selected = (text == currentDeliveryType),
                    onClick = { onSelectDeliveryType(text) }
                )
                NormalText(value = text.value, modifier = Modifier)
            }
        }
    }
}

@Composable
fun ShowSpacer() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun OrderResume(
    totalOrder: Double,
    orderProducts: List<TicketItem>
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GrayNeutral),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.EventNote,
                    contentDescription = null,
                )
                NormalText(
                    value = stringResource(id = R.string.cart_checkout_title),
                    modifier = Modifier,
                )
            }
            ListOrderProductsResume(orderProducts = orderProducts)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                NormalText(
                    value = stringResource(id = R.string.cart_order_total),
                    modifier = Modifier.wrapContentSize(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18
                )
                NormalText(
                    value = "R$: $totalOrder",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentSize(),
                    fontSize = 18,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun SetupDivider() {
    Divider(
        modifier = Modifier.padding(
            vertical = 4.dp
        ),
        color = Color.LightGray
    )
}

@Composable
fun ListOrderProductsResume(
    orderProducts: List<TicketItem>
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(items = orderProducts) { item ->
            OrderProductItem(item)
            SetupDivider()
        }
    }
}

@Composable
fun OrderProductItem(ticketItem: TicketItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NormalText(
            value = ticketItem.product.name,
            modifier = Modifier,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 14
        )
        NormalText(
            value = "R$: ${ticketItem.quantity * ticketItem.product.price}",
            modifier = Modifier,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold,
            fontSize = 12
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NormalText(
            value = "Qnt ${ticketItem.quantity}",
            modifier = Modifier,
            textAlign = TextAlign.Start,
            fontSize = 12
        )
        NormalText(
            value = "${ticketItem.quantity} x R$: ${ticketItem.product.price}",
            modifier = Modifier,
            textAlign = TextAlign.End,
            fontSize = 12
        )
    }
}

@Composable
fun SelectOrderPaymentType(
    paymentMethods: List<PaymentMethod>,
    onPaymentMethodSelected: (PaymentMethod) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GrayNeutral),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            NormalText(
                value = stringResource(id = R.string.cart_payment_method),
                modifier = Modifier
            )
            LazyRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                items(paymentMethods) { payment ->
                    ItemPaymentMethod(
                        payment,
                        onPaymentMethodSelected = { onPaymentMethodSelected(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemPaymentMethod(
    paymentMethod: PaymentMethod,
    onPaymentMethodSelected: (PaymentMethod) -> Unit
) {
    Box(modifier = Modifier
        .padding(8.dp)
        .clickable {
            onPaymentMethodSelected(paymentMethod)
        }) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = paymentMethod.icon,
                contentDescription = null,
                tint = paymentMethod.tint
            )
            NormalText(
                value = paymentMethod.name,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ConfirmOrder(
    confirmPayment: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize()
    ) {
        ButtonPrimary(
            value = stringResource(id = R.string.cart_confirm_payment),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                confirmPayment()
            }
        )
    }
}
