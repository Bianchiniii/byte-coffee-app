package com.bianchini.vinicius.matheus.cupcake.feature.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bianchini.vinicius.matheus.cupcake.R
import com.bianchini.vinicius.matheus.cupcake.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.cupcake.ui.components.HeadingText
import com.bianchini.vinicius.matheus.cupcake.ui.components.NormalText
import com.bianchini.vinicius.matheus.cupcake.ui.components.TextFieldComponents
import com.bianchini.vinicius.matheus.cupcake.ui.components.PasswordTextField

@Composable
fun SignUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp),
        color = Color.White,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalText(
                stringResource(id = R.string.sign_up_title)
            )
            HeadingText(
                stringResource(id = R.string.sign_up_description)
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            TextFieldComponents(
                labelValue = stringResource(id = R.string.sign_up_your_name),
                Icons.Filled.Person
            )
            TextFieldComponents(
                labelValue = stringResource(id = R.string.sign_up_last_name),
                Icons.Filled.Person
            )
            TextFieldComponents(
                labelValue = stringResource(id = R.string.sign_up_email),
                Icons.Filled.Email
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.sign_up_password),
                Icons.Filled.Password
            )
            Spacer(
                modifier = Modifier.height(80.dp)
            )
            ButtonPrimary(
                value = stringResource(id = R.string.sign_up_finish_sign_up),
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}