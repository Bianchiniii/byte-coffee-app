package com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
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
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccountForm
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.domain.model.NewAccountFormError
import com.bianchini.vinicius.matheus.bytecoffee.feature.auth.signup.presentation.event.OnTextInputChangedEventRegister
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
    val form = authViewModel.form.collectAsStateWithLifecycle().value
    val formError by authViewModel.formError.collectAsStateWithLifecycle()
    val isRegisterEnabled by authViewModel.isRegisterEnabled.collectAsStateWithLifecycle()

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
                .background(color = Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
        ) {
            SetupTopInfo()
            SetupNewAccountInfo(authViewModel, form, formError)
            Divider(color = Color.Transparent, thickness = 1.dp, modifier = Modifier.padding(8.dp))
            SetupAddressInfo(authViewModel, form, formError)
            Spacer(modifier = Modifier.weight(1f))
            ButtonPrimary(
                onClick = { authViewModel.registerAccount() },
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(id = R.string.sign_up_finish_sign_up),
                enabled = isRegisterEnabled
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
fun SetupAddressInfo(
    authViewModel: AuthViewModel,
    form: NewAccountForm,
    formError: NewAccountFormError
) {
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
            value = form.address.street,
            labelValue = stringResource(id = R.string.address_street),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            isError = formError.streetError != null,
            supportingText = if (formError.streetError != null) {
                stringResource(id = formError.streetError)
            } else null,
            leadingIcon = Icons.Filled.AddLocation,
            onValueChanged = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnStreetChangedRegister(
                        it
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextFieldComponents(
            value = form.address.neighborhood,
            labelValue = stringResource(id = R.string.address_neighborhood),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Home,
            onValueChanged = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnNeighborhoodChangedRegister(
                        it
                    )
                )
            },
            isError = formError.neighborhoodError != null,
            supportingText = if (formError.neighborhoodError != null) {
                stringResource(id = formError.neighborhoodError)
            } else null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextFieldComponents(
            value = form.address.number,
            labelValue = stringResource(id = R.string.address_number),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Numbers,
            onValueChanged = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnNumberChangedRegister(
                        it
                    )
                )
            },
            isError = formError.numberError != null,
            supportingText = if (formError.numberError != null) {
                stringResource(id = formError.numberError)
            } else null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        TextFieldComponents(
            value = form.address.cityAndState,
            labelValue = stringResource(id = R.string.address_city_state),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.LocationCity,
            isError = formError.cityAndStateError != null,
            supportingText = if (formError.cityAndStateError != null) {
                stringResource(id = formError.cityAndStateError)
            } else null,
            onValueChanged = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnCityAnStateChangedRegister(
                        it
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Composable
fun SetupNewAccountInfo(
    authViewModel: AuthViewModel,
    form: NewAccountForm,
    formError: NewAccountFormError
) {
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
            value = form.profileInfo.name,
            labelValue = stringResource(id = R.string.sign_up_your_name),
            isError = formError.nameError != null,
            supportingText = if (formError.nameError != null) {
                stringResource(id = formError.nameError)
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Person,
            onValueChanged = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnNameChangedRegister(
                        it
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextFieldComponents(
            value = form.profileInfo.surname,
            labelValue = stringResource(id = R.string.sign_up_last_name),
            isError = formError.surnameError != null,
            supportingText = if (formError.surnameError != null) {
                stringResource(id = formError.surnameError)
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Person,
            onValueChanged = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnLastNameChangedRegister(
                        it
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        LoginField(
            value = form.profileInfo.email,
            onChange = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnEmailChangedRegister(
                        it
                    )
                )
            },
            isError = formError.emailError != null,
            supportingText = if (formError.emailError != null) {
                stringResource(id = formError.emailError)
            } else null,
            modifier = Modifier.fillMaxWidth(),
            labelValue = stringResource(id = R.string.sign_up_email),
            placeholder = stringResource(id = R.string.login_email),
        )
        PasswordTextField(
            value = form.profileInfo.password,
            labelValue = stringResource(id = R.string.sign_up_password),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            isError = formError.passwordError != null,
            supportingText = if (formError.passwordError != null) {
                stringResource(id = formError.passwordError)
            } else null,
            leadingIcon = Icons.Filled.Password,
            placeholder = stringResource(id = R.string.sign_up_placeholder_password),
            onValueChanged = {
                authViewModel.onTextInputChangedEventRegister(
                    OnTextInputChangedEventRegister.OnPasswordChangedRegister(
                        it
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
    }
}
