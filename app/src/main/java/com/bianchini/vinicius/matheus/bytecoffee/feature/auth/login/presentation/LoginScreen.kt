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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel.AuthViewModel
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.login.presentation.event.OnTextInputChangedEventLogin
import com.bianchini.vinicius.matheus.bytecoffee.graph.AuthScreenRoutes
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.LoginField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.PasswordTextField
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val loginCredential by authViewModel.loginCredential.collectAsStateWithLifecycle()
    val loginCredentialError by authViewModel.loginCredentialError.collectAsStateWithLifecycle()

    val isLoggingSuccessful by authViewModel.isLoggingSuccessful.collectAsStateWithLifecycle()

    if (isLoggingSuccessful) {
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
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Primary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                ),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(64.dp))
            LoginField(
                value = loginCredential.login,
                onChange = {
                    authViewModel.onTextInputChangedEventLogin(
                        OnTextInputChangedEventLogin.OnEmailChangedLogin(
                            it
                        )
                    )
                },
                isError = loginCredentialError.loginError != null,
                supportingText = if (loginCredentialError.loginError != null) {
                    stringResource(id = loginCredentialError.loginError!!)
                } else null,
                modifier = Modifier.fillMaxWidth(),
                labelValue = "Email",
                placeholder = stringResource(id = R.string.login_email),
            )
            PasswordTextField(
                value = loginCredential.password,
                labelValue = stringResource(id = R.string.sign_up_password),
                placeholder = stringResource(id = R.string.sign_up_placeholder_password),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                leadingIcon = Icons.Filled.Password,
                isError = loginCredentialError.passwordError != null,
                supportingText = if (loginCredentialError.passwordError != null) {
                    stringResource(id = loginCredentialError.passwordError!!)
                } else null,
                onValueChanged = {
                    authViewModel.onTextInputChangedEventLogin(
                        OnTextInputChangedEventLogin.OnPasswordChangedLogin(
                            it
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            ButtonPrimary(
                onClick = { authViewModel.login() },
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(id = R.string.sign_up)
            )
            ClickableText(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.End)
                    .padding(top = 8.dp),
                text = AnnotatedString(stringResource(R.string.sign_up_create_account)),
                style = TextStyle(
                    color = Primary, fontSize = 16.sp
                ),
                onClick = {
                    navController.navigate(AuthScreenRoutes.SignUp.route)
                }
            )
        }
    }
}