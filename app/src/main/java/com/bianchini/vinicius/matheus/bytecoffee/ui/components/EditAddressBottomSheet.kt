package com.bianchini.vinicius.matheus.bytecoffee.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.ByteCoffeeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAddressBottomSheet(
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircleOutline,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(48.dp)
                )
                NormalText(
                    value = stringResource(id = R.string.cart_confirm_order_message),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            ButtonPrimary(
                value = stringResource(id = R.string.cart_finish),
                modifier = Modifier
            ) {
                // TODO: navegar para a tela de meus pedidos!
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditAddressBottomSheetPreview() {
    ByteCoffeeTheme {
        EditAddressBottomSheet(onDismiss = {})
    }
}