package com.bianchini.vinicius.matheus.bytecoffee.feature.home.cart.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation.HomeViewModel
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.ticket.domain.model.TicketItem
import com.bianchini.vinicius.matheus.bytecoffee.graph.HomeScreenRoutes
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background
import com.bianchini.vinicius.matheus.bytecoffee.util.GlideImageLoader

@Composable
fun CartScreen(
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel,
    navController: NavController,
) {
    val products = homeViewModel.ticketState.collectAsStateWithLifecycle().value.products

    val isCartEmpty = products.isEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background)
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            ),
    ) {
        AnimatedVisibility(visible = isCartEmpty) {
            EmptyCart()
        }
        AnimatedVisibility(visible = !isCartEmpty) {
            CartList(
                products = products,
                onConfirmOrder = { homeViewModel.finishOrder() },
                totalOrder = homeViewModel.getTicketTotal(),
                onAddMoreItems = { navController.navigate(HomeScreenRoutes.Home.route) },
                onInciseItem = { id, qnt -> homeViewModel.updateTicketItem(id, qnt) },
                onRemoveItem = { id, qnt -> homeViewModel.updateTicketItem(id, qnt) },
            )
        }
    }
}

@Composable
fun CartList(
    products: List<TicketItem>,
    totalOrder: Double,
    onConfirmOrder: () -> Unit,
    onAddMoreItems: () -> Unit,
    onInciseItem: (String, Int) -> Unit,
    onRemoveItem: (String, Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopOrderDetails(onAddMoreItems = { onAddMoreItems.invoke() })
        ListCartItems(
            ticketItem = products,
            onInciseItem = { id, qnt -> onInciseItem.invoke(id, qnt) },
            onRemoveItem = { id, qnt -> onRemoveItem.invoke(id, qnt) }
        )
        OrderResume(totalOrder)
        ConfirmOrder(onConfirmOrder = { onConfirmOrder.invoke() })
    }
}

@Composable
fun TopOrderDetails(
    onAddMoreItems: () -> Unit
) {
    NormalText(
        value = stringResource(id = R.string.cart_has_items_on_ticket),
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NormalText(
            value = stringResource(id = R.string.cart_items_list),
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Start
        )
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.cart_add_more_items)),
            onClick = { onAddMoreItems.invoke() },
            modifier = Modifier.wrapContentWidth(),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                color = Color.Red
            ),
        )
    }
}

@Composable
fun ListCartItems(
    ticketItem: List<TicketItem>,
    onInciseItem: (String, Int) -> Unit,
    onRemoveItem: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 12.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            items(items = ticketItem) { item ->
                ItemCart(item.product)
                SetupItemQuantity(
                    item.quantity,
                    item.product.price,
                    onInciseItem = {
                        onInciseItem.invoke(
                            item.product.id,
                            item.quantity + 1
                        )
                    },
                    onRemoveItem = {
                        onRemoveItem.invoke(
                            item.product.id,
                            item.quantity - 1
                        )
                    }
                )
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ItemCart(item: Product) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GlideImageLoader(
            modifier = Modifier
                .wrapContentSize()
                .width(120.dp)
                .height(120.dp),
            path = item.image,
        )
        Column(
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            NormalText(
                value = item.name,
                modifier = Modifier.wrapContentSize(),
                fontWeight = FontWeight.Bold,
                fontSize = 14
            )
            NormalText(
                value = item.description,
                modifier = Modifier.wrapContentSize(),
                fontSize = 12
            )
        }
        NormalText(
            value = "R$ ${item.price}",
            modifier = Modifier.wrapContentSize(),
            fontWeight = FontWeight.Bold,
            fontSize = 16
        )
    }
}

@Composable
fun SetupItemQuantity(
    quantity: Int,
    totalTicket: Double,
    onInciseItem: () -> Unit,
    onRemoveItem: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxHeight()) {
            IconButton(
                onClick = { onRemoveItem.invoke() },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Remove,
                    contentDescription = stringResource(id = R.string.product_remove_item),
                    tint = Color.Gray
                )
            }
            NormalText(
                value = quantity.toString(),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically),
                fontSize = 16
            )
            IconButton(
                onClick = { onInciseItem.invoke() },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.product_add_item),
                    tint = Color.Gray
                )
            }

        }
        NormalText(value = "R$: $totalTicket", modifier = Modifier.fillMaxHeight())
    }
}

@Composable
fun ConfirmOrder(
    onConfirmOrder: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonPrimary(
            onClick = { onConfirmOrder.invoke() },
            modifier = Modifier.fillMaxWidth(),
            value = stringResource(id = R.string.cart_confirm_order),
        )
    }
}

@Composable
fun OrderResume(
    totalOrder: Double
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        NormalText(
            value = stringResource(id = R.string.cart_order_total),
            modifier = Modifier.wrapContentSize(),
            fontSize = 18
        )
        NormalText(
            value = "R$: $totalOrder",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentSize(),
            fontSize = 18
        )
    }
}

@Composable
fun EmptyCart() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painter = painterResource(R.drawable.empty_cart), contentDescription = "")
        NormalText(
            value = stringResource(id = R.string.cart_empty_state_title),
            modifier = Modifier.wrapContentSize(),
            fontWeight = FontWeight.Bold,
            fontSize = 16
        )
        Spacer(modifier = Modifier.height(4.dp))
        NormalText(
            value = stringResource(id = R.string.cart_empty_state_message),
            modifier = Modifier.wrapContentSize(),
            fontSize = 14,
            fontWeight = FontWeight.Bold,
            textColor = Color.White
        )
    }
}