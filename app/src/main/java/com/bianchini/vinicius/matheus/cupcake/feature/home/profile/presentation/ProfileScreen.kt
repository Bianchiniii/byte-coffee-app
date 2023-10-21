package com.bianchini.vinicius.matheus.cupcake.feature.home.profile.presentation

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bianchini.vinicius.matheus.cupcake.R
import com.bianchini.vinicius.matheus.cupcake.ui.components.NormalText
import com.bianchini.vinicius.matheus.cupcake.ui.components.PasswordTextField
import com.bianchini.vinicius.matheus.cupcake.ui.components.TextFieldComponents

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(paddingValues = paddingValues),
    ) {
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
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                profileUri?.let {
                    bitmap.value = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)

                        ImageDecoder.decodeBitmap(source)
                    }
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

                Button(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        profilePhotoPicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                ) {
                    NormalText(
                        modifier = Modifier.wrapContentSize(),
                        value = stringResource(R.string.profile_change_photo)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                TextFieldComponents(
                    labelValue = stringResource(id = R.string.sign_up_your_name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    leadingIcon = Icons.Filled.Person,
                    onValueChanged = {

                    }
                )
                TextFieldComponents(
                    labelValue = stringResource(id = R.string.sign_up_last_name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    Icons.Filled.Person,
                    onValueChanged = {

                    }
                )
                TextFieldComponents(
                    labelValue = stringResource(id = R.string.sign_up_email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    Icons.Filled.Email,
                    onValueChanged = {

                    }
                )
                PasswordTextField(
                    labelValue = stringResource(id = R.string.profile_change_password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    leadingIcon = Icons.Filled.Password,
                    placeholder = stringResource(id = R.string.sign_up_placeholder_password),
                    onValueChanged = {

                    }
                )
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
            ) {
                Text(
                    text = stringResource(R.string.profile_save_changes)
                )
            }
            // TODO: address card
            /*Card {

            }*/
        }
    }
}