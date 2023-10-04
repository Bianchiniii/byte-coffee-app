package com.bianchini.vinicius.matheus.cupcake.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bianchini.vinicius.matheus.cupcake.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageLoader(
    modifier: Modifier,
    path: String,
    contentDescription: String = "load image",
    @DrawableRes fallback: Int = R.drawable.ic_img_loading_error,
    @DrawableRes placeHolder: Int = R.drawable.ic_img_placeholder,
) {
    GlideImage(
        model = path,
        contentDescription = contentDescription,
        modifier = modifier
    ) {
        it.error(fallback)
            .placeholder(placeHolder)
            .load(path)
    }
}