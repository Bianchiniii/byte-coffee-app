package com.bianchini.vinicius.matheus.cupcake.feature.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bianchini.vinicius.matheus.cupcake.R

interface ImageLoader {

    fun load(
        imageView: ImageView,
        imageUrl: String,
        @DrawableRes fallback: Int = R.drawable.ic_img_loading_error,
        @DrawableRes placeHolder: Int = R.drawable.ic_img_placeholder,
    )
}