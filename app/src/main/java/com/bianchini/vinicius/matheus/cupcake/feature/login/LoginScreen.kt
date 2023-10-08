package com.bianchini.vinicius.matheus.cupcake.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bianchini.vinicius.matheus.cupcake.R
import com.bianchini.vinicius.matheus.cupcake.ui.components.HeadingText
import com.bianchini.vinicius.matheus.cupcake.ui.components.LoginField
import com.bianchini.vinicius.matheus.cupcake.ui.components.PasswordTextField

@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(24.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeadingText(
                value = "App nome",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32
            )
            Spacer(modifier = Modifier.height(64.dp))
            LoginField(
                onChange = {},
                modifier = Modifier.fillMaxWidth(),
                labelValue = "Email",
                placeholder = "insira seu email",
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.sign_up_password),
                placeholder = stringResource(id = R.string.sign_up_placeholder_password),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                leadingIcon = Icons.Filled.Password,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.sign_up))
            }
            Text(
                text = stringResource(R.string.sign_up_create_account),
                textAlign = TextAlign.End,
                color = Color.Blue,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.End)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}