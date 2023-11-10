package com.bianchini.vinicius.matheus.bytecoffee.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.BgColor
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary

@Composable
fun TextFieldComponents(
    value: String,
    labelValue: String,
    modifier: Modifier,
    leadingIcon: ImageVector,
    onValueChanged: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    supportingText: String? = null,
    isError: Boolean = false
) {

    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = labelValue) },
        value = value,
        singleLine = true,
        onValueChange = {
            onValueChanged(it)
        },
        isError = isError,
        supportingText = {
            if (supportingText != null && isError) {
                Text(
                    text = supportingText,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BgColor,
            unfocusedContainerColor = BgColor,
            disabledContainerColor = BgColor,
            cursorColor = Primary,
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
        ),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = Primary
            )
        },
    )
}

@Composable
fun PasswordTextField(
    value: String,
    labelValue: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier,
    onValueChanged: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    supportingText: String? = null,
    isError: Boolean = false
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = labelValue) },
        placeholder = { Text(text = placeholder) },
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
        isError = isError,
        supportingText = {
            if (supportingText != null && isError) {
                Text(
                    text = supportingText,
                    modifier = Modifier.fillMaxWidth()
                )
            }
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
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
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
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelValue: String,
    placeholder: String,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(onNext = {
        focusManager.moveFocus(
            FocusDirection.Down
        )
    }),
    supportingText: String? = null,
    isError: Boolean = false
) {
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = Primary
        )
    }

    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = labelValue) },
        value = value,
        placeholder = { Text(text = placeholder) },
        onValueChange = {
            onChange(it)
        },
        isError = isError,
        supportingText = {
            if (supportingText != null && isError) {
                Text(
                    text = supportingText,
                    modifier = Modifier.fillMaxWidth()
                )
            }
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
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}