package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.commun.viewmodel.AuthViewModel
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.LoginField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.PasswordTextField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.TextFieldComponents
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Primary

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    val successCreateAccount by authViewModel.successCreateAccount.collectAsStateWithLifecycle()

    if (successCreateAccount) {
        navController.popBackStack()
        navController.navigate(Graph.HOME)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(24.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Top,
        ) {
            SetupTopInfo()
            SetupNewAccountInfo(authViewModel)
            Divider(color = Color.Transparent, thickness = 1.dp, modifier = Modifier.padding(8.dp))
            SetupAddressInfo(authViewModel)
            Spacer(modifier = Modifier.weight(1f))
            ButtonPrimary(
                onClick = { authViewModel.registerAccount() },
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(id = R.string.sign_up_finish_sign_up),
            )
        }
    }
}

@Composable
fun SetupTopInfo() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            ),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun SetupAddressInfo(authViewModel: AuthViewModel) {
    Column(Modifier.fillMaxWidth()) {
        NormalText(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start),
            value = stringResource(id = R.string.address_title),
            fontWeight = FontWeight.Bold,
            fontSize = 18,
            textAlign = TextAlign.Start,
        )
        TextFieldComponents(
            labelValue = stringResource(id = R.string.address_street),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.AddLocation,
            onValueChanged = { authViewModel.updateProfileAddressStreet(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextFieldComponents(
            labelValue = stringResource(id = R.string.address_neighborhood),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Home,
            onValueChanged = { authViewModel.updateProfileAddressNeighborhood(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextFieldComponents(
            labelValue = stringResource(id = R.string.address_number),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Numbers,
            onValueChanged = { authViewModel.updateProfileAddressNumber(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        TextFieldComponents(
            labelValue = stringResource(id = R.string.address_city_state),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.LocationCity,
            onValueChanged = { authViewModel.updateProfileAddressCityAndState(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Composable
fun SetupNewAccountInfo(authViewModel: AuthViewModel) {
    Column(Modifier.wrapContentHeight()) {
        NormalText(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start),
            value = stringResource(id = R.string.sign_up_personal_info),
            fontWeight = FontWeight.Bold,
            fontSize = 18,
            textAlign = TextAlign.Start,
        )
        TextFieldComponents(
            labelValue = stringResource(id = R.string.sign_up_your_name),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Person,
            onValueChanged = { authViewModel.updateProfileInfoName(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextFieldComponents(
            labelValue = stringResource(id = R.string.sign_up_last_name),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            Icons.Filled.Person,
            onValueChanged = { authViewModel.updateProfileInfoSurname(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        LoginField(
            onChange = { authViewModel.updateProfileInfoEmail(it) },
            modifier = Modifier.fillMaxWidth(),
            labelValue = stringResource(id = R.string.sign_up_email),
            placeholder = stringResource(id = R.string.login_email),
        )
        PasswordTextField(
            labelValue = stringResource(id = R.string.sign_up_password),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Password,
            placeholder = stringResource(id = R.string.sign_up_placeholder_password),
            onValueChanged = { authViewModel.updateProfileInfoPassword(it) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
    }
}
