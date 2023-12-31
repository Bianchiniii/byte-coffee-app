package com.bianchini.vinicius.matheus.bytecoffee.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary

@Composable
fun ButtonPrimary(
    value: String,
    modifier: Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        contentPadding = PaddingValues(),
        colors = if (enabled) ButtonDefaults.buttonColors(Primary) else ButtonDefaults.buttonColors(
            contentColor = Color.Gray
        ),
        enabled = enabled
    ) {
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}