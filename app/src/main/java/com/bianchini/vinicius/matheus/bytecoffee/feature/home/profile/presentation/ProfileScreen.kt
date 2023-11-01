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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
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
import com.bianchini.vinicius.matheus.bytecoffee.graph.Graph
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.ButtonPrimary
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.LoginField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.PasswordTextField
import com.bianchini.vinicius.matheus.bytecoffee.ui.components.TextFieldComponents
import com.bianchini.vinicius.matheus.bytecoffee.ui.theme.Background

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    profileViewModel: ProfileViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background)
            .padding(
                start = 16.dp,
                bottom = paddingValues.calculateBottomPadding(),
                top = 16.dp,
                end = 16.dp
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SetupAvatar(profileViewModel)
            Spacer(modifier = Modifier.height(36.dp))
            ChangeProfileInfo()
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
        // TODO: IMPLEMENTAR NAV PARA O GRAPH HOME
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val profilePhoto = profileViewModel.profilePhoto.collectAsStateWithLifecycle()

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

        IconButton(
            onClick = { profileViewModel.logout() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_logout),
                contentDescription = stringResource(
                    id = R.string.profile_logout
                )
            )
        }

        // TODO: refatorar para recuperar a imagem do storage
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

        ButtonPrimary(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onClick = {
                profilePhotoPicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
            value = stringResource(R.string.profile_change_photo)
        )
    }
}

@Composable
fun ChangeProfileInfo() {
    Column(modifier = Modifier.wrapContentSize()) {
        TextFieldComponents(
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
            onChange = { },
            modifier = Modifier.fillMaxWidth(),
            labelValue = "Email",
            placeholder = stringResource(id = R.string.login_email),
        )
        PasswordTextField(
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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp)
        ) {
            ButtonPrimary(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp)
                    .align(Alignment.Bottom),
                value = stringResource(R.string.profile_save_changes)
            )
        }
    }
}