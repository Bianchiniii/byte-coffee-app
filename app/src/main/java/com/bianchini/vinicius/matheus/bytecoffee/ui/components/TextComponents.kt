package com.bianchini.vinicius.matheus.bytecoffee.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun NormalText(
    modifier: Modifier,
    value: String,
) {
    Text(
        text = value,
        modifier = modifier,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        textAlign = TextAlign.Center
    )
}


@Composable
fun HeadingText(
    value: String,
    fontSize: Int = 18,
    modifier: Modifier,
) {
    Text(
        text = value,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        ),
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )
}