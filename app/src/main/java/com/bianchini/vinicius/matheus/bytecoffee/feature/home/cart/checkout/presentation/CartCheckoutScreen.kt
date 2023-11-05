package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType.DELIVERY
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.DeliveryType.PICKUP
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.PaymentMethod
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.EditAddressBottomSheet
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartCheckoutScreen(
    navController: NavController
) {
    val currentDeliveryType = remember {
        mutableStateOf(PICKUP)
    }

    var showEditAddressBottomSheet by remember { mutableStateOf(false) }

    if (showEditAddressBottomSheet) {
        EditAddressBottomSheet { showEditAddressBottomSheet = false }
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
        Column(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
                top = it.calculateTopPadding(),
                bottom = 24.dp
            )
        ) {
            SelectOrderDeliveryType(
                onSelectDeliveryType = {
                    currentDeliveryType.value = it
                },
                currentDeliveryType = currentDeliveryType.value
            )
            DeliveryTypeInfo(
                deliveryType = currentDeliveryType.value,
                // TODO: trazer indos da loja e do profile
                streetAndNumber = "Rua das Flores, 123",
                neighborhoodAndCityAndState = "Centro, São Paulo - SP",
                editAddress = {

                }
            )
            ShowSpacer()
            OrderResume()
            ShowSpacer()
            SelectOrderPaymentType(
                // TODO: retornado pelo back
                listOf(
                    PaymentMethod(
                        id = "1",
                        name = "Dinheiro",
                        icon = Icons.Outlined.Money
                    ),
                    PaymentMethod(
                        id = "1",
                        name = "Cartão",
                        icon = Icons.Outlined.CreditCard
                    )
                )
            )
            ShowSpacer()
            ConfirmOrder(
                confirmPayment = {

                }
            )
        }
    }
}

@Composable
fun DeliveryTypeInfo(
    deliveryType: DeliveryType,
    streetAndNumber: String,
    neighborhoodAndCityAndState: String,
    editAddress: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
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
            value = streetAndNumber,
            modifier = Modifier
        )
        NormalText(
            value = neighborhoodAndCityAndState,
            modifier = Modifier,
        )
        if (deliveryType == DELIVERY) {
            ShowSpacer()
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(size = 16.dp))
                    .padding(4.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(size = 16.dp)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { editAddress() }) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null
                    )
                }
                NormalText(
                    value = stringResource(id = R.string.cart_checkout_edit_address),
                    modifier = Modifier
                )
            }
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
        editAddress = {}
    )
}


@Composable
fun SelectOrderDeliveryType(
    onSelectDeliveryType: (DeliveryType) -> Unit,
    currentDeliveryType: DeliveryType
) {
    val selectedBackgroundColor = Primary
    val selectedTextColor = Color.White
    val unselectedBackgroundColor = Color.White
    val unselectedTextColor = TextColor

    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Box(
            modifier = Modifier
                .weight(0.50f)
                .height(48.dp)
                .clickable { onSelectDeliveryType(PICKUP) }
                .background(
                    color = if (currentDeliveryType.type == PICKUP.type) {
                        selectedBackgroundColor
                    } else unselectedBackgroundColor,
                    shape = RoundedCornerShape(size = 14.dp)
                )
        ) {
            NormalText(
                value = stringResource(id = R.string.cart_checkout_pickup),
                modifier = Modifier.align(Alignment.Center),
                textColor = if (currentDeliveryType == PICKUP) {
                    selectedTextColor
                } else unselectedTextColor
            )
        }
        Box(
            modifier = Modifier
                .weight(0.50f)
                .height(48.dp)
                .clickable { onSelectDeliveryType(DELIVERY) }
                .background(
                    color = if (currentDeliveryType == DELIVERY) {
                        unselectedBackgroundColor
                    } else selectedBackgroundColor,
                    shape = RoundedCornerShape(size = 14.dp)
                )
        ) {
            NormalText(
                value = stringResource(id = R.string.cart_checkout_delivery),
                modifier = Modifier.align(Alignment.Center),
                textColor = if (currentDeliveryType == DELIVERY) {
                    unselectedTextColor
                } else selectedTextColor
            )
        }
    }
}

@Composable
fun ShowSpacer() {
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun OrderResume() {
    Column(
        modifier = Modifier.background(Color.LightGray)
    ) {

    }
}

@Composable
fun SelectOrderPaymentType(
    paymentMethods: List<PaymentMethod>
) {
    Column(
        modifier = Modifier.background(Color.LightGray)
    ) {
        NormalText(
            value = stringResource(id = R.string.cart_payment_method),
            modifier = Modifier
        )
        ShowSpacer()
        LazyRow {
            items(paymentMethods) { payment ->
                ItemPaymentMethod(payment)
            }
        }
    }
}

@Composable
fun ItemPaymentMethod(paymentMethod: PaymentMethod) {
    Column {
        Icon(imageVector = paymentMethod.icon, contentDescription = null)
        NormalText(
            value = paymentMethod.name,
            modifier = Modifier
        )
    }
}

@Composable
fun ConfirmOrder(
    confirmPayment: () -> Unit
) {
    Column {
        ButtonPrimary(
            value = stringResource(id = R.string.cart_confirm_payment),
            modifier = Modifier.fillMaxWidth()
        ) {
            confirmPayment()
        }
    }
}
