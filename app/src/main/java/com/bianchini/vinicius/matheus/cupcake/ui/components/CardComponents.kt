package com.bianchini.vinicius.matheus.cupcake.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bianchini.vinicius.matheus.cupcake.R
import com.bianchini.vinicius.matheus.cupcake.feature.home.aisle.domain.Food

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
            HeadingText(
                value = stringResource(R.string.product_details_about),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            HeadingText(
                value = food.description,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Start)
            )
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = stringResource(R.string.product_details_add_item))
                }
            }
        }
    }
}

@Composable
fun ListFoodSize(
    foodName: String,
    sizes: List<Food.Size>
) {
    HeadingText(
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
    ButtonClicked(
        value = size.label,
        onClick = { },
        clickedColor = Color.Green,
        defaultColor = Color.Transparent,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfCardProductDetail() {
    CardProductDetail(
        modifier = Modifier.fillMaxSize(),
        Food(
            id = 3,
            name = "Nescau",
            description = "com leite",
            prince = 7.50,
            size = listOf(
                Food.Size.SMALL, Food.Size.MEDIUM, Food.Size.LARGE
            ),
            image = "data:image/jpeg;base64,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",
        )
    )
}