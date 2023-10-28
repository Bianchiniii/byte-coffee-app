package com.bianchini.vinicius.matheus.bytecoffee.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.bianchini.vinicius.matheus.bytecoffee.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImageLoader(
    modifier: Modifier,
    path: String,
    contentDescription: String = stringResource(id = R.string.util_image),
    contentScale: ContentScale = ContentScale.Fit,
    @DrawableRes fallback: Int = R.drawable.ic_img_loading_error,
    @DrawableRes placeHolder: Int = R.drawable.ic_img_placeholder,
) {
    GlideImage(
        model = path,
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier
    ) {
        it.error(fallback)
            .placeholder(placeHolder)
            .load(path)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }
}