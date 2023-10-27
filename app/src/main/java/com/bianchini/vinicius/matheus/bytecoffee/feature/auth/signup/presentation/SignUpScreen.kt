package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel.AuthViewModel
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.HeadingText
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.PasswordTextField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.TextFieldComponents
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val uiStateError by authViewModel.uiStateError.collectAsStateWithLifecycle()
    val successCreateAccount by authViewModel.successCreateAccount.collectAsStateWithLifecycle()

    if (successCreateAccount) {
        navController.popBackStack()
        navController.navigate(Graph.HOME)
    }

    if (uiStateError) {
        scope.launch {
            snackbarHostState.showSnackbar(
                "Erro ao criar conta"
            )
        }
    }

    var registerNameValue by remember {
        mutableStateOf("")
    }
    var registerSurnameValue by remember {
        mutableStateOf("")
    }
    var registerEmailValue by remember {
        mutableStateOf("")
    }
    var registerPasswordValue by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(24.dp),
        contentColor = Color.White,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NormalText(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp),
                stringResource(id = R.string.sign_up_title)
            )
            HeadingText(
                stringResource(id = R.string.sign_up_description),
                modifier = Modifier.wrapContentSize()
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            TextFieldComponents(
                labelValue = stringResource(id = R.string.sign_up_your_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                leadingIcon = Icons.Filled.Person,
                onValueChanged = { registerNameValue = it }
            )
            TextFieldComponents(
                labelValue = stringResource(id = R.string.sign_up_last_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                Icons.Filled.Person,
                onValueChanged = { registerSurnameValue = it }
            )
            TextFieldComponents(
                labelValue = stringResource(id = R.string.sign_up_email),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                Icons.Filled.Email,
                onValueChanged = { registerEmailValue = it }
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.sign_up_password),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                leadingIcon = Icons.Filled.Password,
                placeholder = stringResource(id = R.string.sign_up_placeholder_password),
                onValueChanged = { registerPasswordValue = it }
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonPrimary(
                onClick = {
                    authViewModel.registerAccount(
                        email = registerEmailValue,
                        name = registerNameValue,
                        surname = registerSurnameValue,
                        password = registerPasswordValue
                    )
                },
                value = stringResource(id = R.string.sign_up_finish_sign_up),
                firstGradientColor = MaterialTheme.colorScheme.primary,
                secondGradientColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}