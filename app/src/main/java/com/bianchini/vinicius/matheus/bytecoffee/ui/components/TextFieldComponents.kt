package com.bianchini.vinicius.matheus.bytecoffee.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.BgColor
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary

@Composable
fun TextFieldComponents(
    labelValue: String,
    modifier: Modifier,
    leadingIcon: ImageVector,
    onValueChanged: (String) -> Unit,
    keyboardActions: KeyboardOptions = KeyboardOptions.Default
) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = labelValue) },
        value = textValue.value,
        singleLine = true,
        onValueChange = {
            textValue.value = it

            onValueChanged(it)
        },

        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        keyboardActions = KeyboardActions.Default,
        keyboardOptions = keyboardActions,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = Primary
            )
        }
    )
}

@Composable
fun PasswordTextField(
    labelValue: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier,
    onValueChanged: (String) -> Unit
) {
    val passwordValue = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = labelValue) },
        placeholder = { Text(text = placeholder) },
        value = passwordValue.value,
        onValueChange = {
            passwordValue.value = it

            onValueChanged(it)
        },
        singleLine = true,
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
                contentDescription = null,
                tint = Primary
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
                Icon(imageVector = icon, contentDescription = description, tint = Primary)
            }
        },
        visualTransformation = if (passwordVisible.value) {
            VisualTransformation.None
        } else PasswordVisualTransformation()
    )
}


@Composable
fun LoginField(
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelValue: String,
    placeholder: String,
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = Primary
        )
    }

    val emailValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = labelValue) },
        value = emailValue.value,
        placeholder = { Text(text = placeholder) },
        onValueChange = {
            emailValue.value = it

            onChange(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        leadingIcon = leadingIcon,
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}