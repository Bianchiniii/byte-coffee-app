package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.checkout.domain.model.PaymentMethod
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartCheckoutScreen(
    navController: NavController
) {
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
        Column(modifier = Modifier.padding(paddingValues = it)) {
            SelectOrderDeliveryType()
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
fun SelectOrderDeliveryType() {
    Column(
        modifier = Modifier.background(Color.LightGray)
    ) {

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
