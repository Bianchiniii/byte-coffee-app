package com.bianchini.vinicius.matheus.cupcake.feature.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bianchini.vinicius.matheus.cupcake.R
import com.bianchini.vinicius.matheus.cupcake.graph.Graph
import com.bianchini.vinicius.matheus.cupcake.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.cupcake.ui.components.HeadingText
import com.bianchini.vinicius.matheus.cupcake.ui.components.NormalText
import com.bianchini.vinicius.matheus.cupcake.ui.components.PasswordTextField
import com.bianchini.vinicius.matheus.cupcake.ui.components.TextFieldComponents

@Composable
fun SignUpScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(24.dp),
        color = Color.White,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalText(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp),
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
                onClick = {
                    // TODO: adicionar validações de form
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                value = stringResource(id = R.string.sign_up_finish_sign_up),
                firstGradientColor = MaterialTheme.colorScheme.primary,
                secondGradientColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen(navController = rememberNavController())
}