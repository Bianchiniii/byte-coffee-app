package com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Category
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.aisle.domain.model.Product
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.commun.presentation.HomeViewModel
import com.bianchini.vinicius.matheus.bytecoffee.graph.DetailsScreenRoutes
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.graph.HomeNavGraph
import com.bianchini.vinicius.matheus.bytecoffee.graph.HomeScreenRoutes
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Secondary
import com.bianchini.vinicius.matheus.bytecoffee.util.GlideImageLoader

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        },
        modifier = Modifier.fillMaxSize(),
        content = {
            HomeNavGraph(
                navController,
                it,
            )
        }
    )
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val screens = listOf(
        HomeScreenRoutes.Home,
        HomeScreenRoutes.Cart,
        HomeScreenRoutes.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach { screen ->
                NavigationBarItem(
                    colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                        indicatorColor = Secondary,
                    ),
                    icon = { Icon(screen.icon, contentDescription = screen.title, tint = Primary) },
                    label = { Text(text = screen.title) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun ContentHomeScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
) {
    val profile = viewModel.userProfile.collectAsStateWithLifecycle()
    val categories = viewModel.categories.collectAsStateWithLifecycle()
    val products = viewModel.products.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 12.dp,
                end = 12.dp,
                top = 12.dp,
                bottom = paddingValues.calculateBottomPadding(),
            )
            .background(color = Background),
    ) {
        ProfileInfoAndNotification(
            profile.value?.name,
            onNavigateToOrders = { navController.navigate(Graph.ORDERS) }
        )
        Spacer(modifier = Modifier.height(15.dp))
        AnimatedVisibility(visible = !categories.value.isNullOrEmpty()) {
            // TODO: sera implementado futuramente
            ListCategories(categories.value!!)
        }
        AnimatedVisibility(visible = !products.value.isNullOrEmpty()) {
            ListFoodItems(
                navController = navController,
                productsList = products.value!!
            )
        }
    }
}

@Composable
fun ListCategories(categories: List<Category>) {
    var selectedCategory by remember {
        mutableStateOf(categories.first().id)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyRow {
            items(items = categories) { category ->
                CategoryItem(
                    category,
                    isSelected = selectedCategory == category.id,
                    onCategoryClick = {
                        selectedCategory = category.id
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onCategoryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 4.dp
            )
    ) {
        Button(
            onClick = { onCategoryClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Primary else Color.Transparent,
            ),
        ) {
            Image(imageVector = category.icon, contentDescription = category.name)
            NormalText(
                value = category.name,
                fontSize = 14,
                modifier = Modifier.wrapContentSize(),
                textColor = if (isSelected) Color.White else Color.Gray,
            )
        }

    }
}

@Composable
fun ListFoodItems(
    navController: NavController,
    productsList: List<Product>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.wrapContentSize()
    ) {
        items(items = productsList) { product ->
            ProductListItem(product) {
                navController.navigate(
                    DetailsScreenRoutes.ProductScreen.route.replace(
                        "{productId}",
                        product.id
                    )
                )
            }
        }
    }
}

@Composable
fun ProductListItem(
    food: Product,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
            .clickable { onClick() }
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            GlideImageLoader(
                modifier = Modifier.fillMaxWidth(),
                path = food.image,
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            ) {
                NormalText(
                    value = food.name,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14,
                )
                Spacer(modifier = Modifier.height(4.dp))
                NormalText(
                    value = food.description,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 12,
                )
                NormalText(
                    value = "R$ ${food.price}",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14,
                )
            }
        }
    }
}

@Composable
fun ProfileInfoAndNotification(
    profileName: String?,
    onNavigateToOrders: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            NormalText(
                value = "Olá, ${profileName ?: "visitante"}",
                fontSize = 20,
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            NormalText(
                value = "continue conectado e cafeinado!",
                fontSize = 16,
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Start,
            )
        }
        IconButton(onClick = { onNavigateToOrders() }) {
            Icon(
                Icons.Filled.Notifications,
                contentDescription = stringResource(R.string.util_notification_icon),
            )
        }
    }
}