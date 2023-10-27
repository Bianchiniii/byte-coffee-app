package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.HeadingText
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background

@Composable
fun CartScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background)
            .padding(
                8.dp,
                bottom = paddingValues.calculateBottomPadding()
            ),
    ) {
        EmptyCart()
    }
}

@Composable
fun EmptyCart() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painter = painterResource(R.drawable.empty_cart), contentDescription = "")
        HeadingText(
            value = stringResource(id = R.string.cart_empty_state_title),
            modifier = Modifier.wrapContentSize(),
            fontWeight = FontWeight.Bold,
            fontSize = 16
        )
        Spacer(modifier = Modifier.height(4.dp))
        HeadingText(
            value = stringResource(id = R.string.cart_empty_state_message),
            modifier = Modifier.wrapContentSize(),
            fontSize = 14
        )
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(paddingValues = PaddingValues())
}