package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.presentation

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel.AuthViewModel
import com.bianchini.vinicius.matheus.bytecoffee.graph.AuthScreenRoutes
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.HeadingText
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.LoginField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.PasswordTextField

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var emailValue by remember {
        mutableStateOf("")
    }

    var passwordValue by remember {
        mutableStateOf("")
    }

    val isLoggingSuccessful = authViewModel.isLoggingSuccessful.collectAsStateWithLifecycle()

    if (isLoggingSuccessful.value) {
        navController.popBackStack()
        navController.navigate(Graph.HOME)
    }

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
                value = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32
            )
            Spacer(modifier = Modifier.height(64.dp))
            LoginField(
                onChange = { emailValue = it },
                modifier = Modifier.fillMaxWidth(),
                labelValue = "Email",
                placeholder = stringResource(id = R.string.login_email),
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.sign_up_password),
                placeholder = stringResource(id = R.string.sign_up_placeholder_password),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                leadingIcon = Icons.Filled.Password,
                onValueChanged = { passwordValue = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    authViewModel.login(
                        email = emailValue,
                        password = passwordValue
                    )
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.sign_up))
            }
            Button(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                onClick = {
                    navController.navigate(AuthScreenRoutes.SignUp.route)
                }) {
                Text(
                    text = stringResource(R.string.sign_up_create_account),
                    textAlign = TextAlign.End,
                    color = Color.Blue,
                )
            }
        }
    }
}