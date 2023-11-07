package com.bianchini.vinicius.matheus.bytecoffee.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.TextColor


@Composable
fun NormalText(
    value: String,
    fontSize: Int = 18,
    modifier: Modifier,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight? = null,
    textColor: Color = TextColor,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        maxLines = maxLines,
        text = value,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = fontSize.sp,
            color = textColor
        ),
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        fontWeight = fontWeight
    )
}