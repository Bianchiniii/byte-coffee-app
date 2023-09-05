package com.bianchini.vinicius.matheus.cupcake.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bianchini.vinicius.matheus.cupcake.R
import com.bianchini.vinicius.matheus.cupcake.ui.theme.BgColor
import com.bianchini.vinicius.matheus.cupcake.ui.theme.Primary

@Composable
fun TextFieldComponents(
    labelValue: String,
    leadingIcon: ImageVector
) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = labelValue) },
        value = textValue.value,
        onValueChange = { textValue.value = it },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        keyboardActions = KeyboardActions.Default,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        }
    )
}

@Composable
fun PasswordTextField(
    labelValue: String,
    leadingIcon: ImageVector
) {
    val passwordValue = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = labelValue) },
        value = passwordValue.value,
        onValueChange = { passwordValue.value = it },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        keyboardActions = KeyboardActions.Default,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        trailingIcon = {
            val icon = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else Icons.Filled.VisibilityOff

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.sign_up_hide_password)
            } else stringResource(id = R.string.sign_up_show_password)

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = icon, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) {
            VisualTransformation.None
        } else PasswordVisualTransformation()
    )
}