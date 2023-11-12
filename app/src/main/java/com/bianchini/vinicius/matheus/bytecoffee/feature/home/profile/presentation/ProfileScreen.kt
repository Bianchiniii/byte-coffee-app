package com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.presentation

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bianchini.vinicius.matheus.bytecoffee.feature.home.profile.domain.model.EditAccountForm
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.LoginField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.NormalText
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.PasswordTextField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.TextFieldComponents
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    profileViewModel: ProfileViewModel,
) {
    val form by profileViewModel.form.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background)
            .padding(
                start = 24.dp,
                bottom = paddingValues.calculateBottomPadding(),
                top = 24.dp,
                end = 24.dp
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SetupAvatar(profileViewModel)
            Spacer(modifier = Modifier.height(36.dp))
            ChangeProfileInfo(form)
            Divider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color.LightGray
            )
            SetupLogout(
                onLogoutClick = { profileViewModel.logout() }
            )
        }
    }
}

@Composable
fun SetupAvatar(profileViewModel: ProfileViewModel) {
    val context = LocalContext.current

    var profileUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val profilePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri ->
        uri?.let {
            profileUri = it

            profileViewModel.saveProfilePhoto(it)
        }
    }

    val navController = rememberNavController()

    val shouldLogout = profileViewModel.shouldRedirectToAuth.collectAsStateWithLifecycle()

    if (shouldLogout.value) {
        navController.popBackStack(Graph.HOME, true)
        navController.navigate(Graph.AUTHENTICATION)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                profilePhotoPicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*  val profilePhoto = profileViewModel.profilePhoto.collectAsStateWithLifecycle()

          if (!profilePhoto.value.isNullOrEmpty()) {
              profileUri = profilePhoto.value!!.toUri()

              bitmap.value = if (Build.VERSION.SDK_INT < 28) {
                  MediaStore.Images.Media.getBitmap(context.contentResolver, profileUri!!)
              } else {
                  val source =
                      ImageDecoder.createSource(context.contentResolver, profileUri!!)

                  ImageDecoder.decodeBitmap(source)
              }
          } else {
              bitmap.value = null
          }
  */

        // TODO: implementar alteração de foto
        bitmap.value?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = stringResource(R.string.profile_photo_image),
                modifier = Modifier
                    .size(135.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.Crop,
            )
        } ?: run {
            Image(
                modifier = Modifier
                    .size(135.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.ic_avatar_fallback),
                contentDescription = stringResource(R.string.profile_photo_image),
            )
        }
    }
}

@Composable
fun ChangeProfileInfo(form: EditAccountForm) {
    Column(modifier = Modifier.wrapContentSize()) {
        TextFieldComponents(
            value = form.name,
            labelValue = stringResource(id = R.string.sign_up_your_name),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Person,
            onValueChanged = {

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        TextFieldComponents(
            value = form.lastName,
            labelValue = stringResource(id = R.string.sign_up_last_name),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            Icons.Filled.Person,
            onValueChanged = {

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        LoginField(
            value = form.email,
            onChange = { },
            modifier = Modifier.fillMaxWidth(),
            labelValue = "Email",
            placeholder = stringResource(id = R.string.login_email),
        )
        PasswordTextField(
            value = form.password,
            labelValue = stringResource(id = R.string.profile_change_password),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            leadingIcon = Icons.Filled.Password,
            placeholder = stringResource(id = R.string.sign_up_placeholder_password),
            onValueChanged = {

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        ButtonPrimary(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(),
            value = stringResource(R.string.profile_save_changes)
        )
    }
}

@Composable
fun SetupLogout(
    onLogoutClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onLogoutClick()
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_logout),
            contentDescription = stringResource(
                id = R.string.profile_logout
            ),
            modifier = Modifier.size(24.dp)
        )

        NormalText(
            value = stringResource(id = R.string.profile_logout),
            modifier = Modifier,
            fontSize = 16
        )
    }

}