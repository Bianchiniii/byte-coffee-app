package com.bianchini.vinicius.matheus.cupcake.feature.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader {
    override fun load(
        imageView: ImageView,
        imageUrl: String,
        @DrawableRes fallback: Int,
        @DrawableRes placeHolder: Int
    ) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .fallback(fallback)
            .placeholder(placeHolder)
            .into(imageView)
    }
}