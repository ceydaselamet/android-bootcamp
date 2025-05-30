package com.ceyda.androidbootcampodev.foodorderingapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ceyda.androidbootcampodev.foodorderingapp.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageUtils @Inject constructor() {
    
    companion object {
        private const val BASE_IMAGE_URL = "http://kasimadalan.pe.hu/yemekler/resimler/"
    }
    
    fun loadYemekImage(imageView: ImageView, yemekResimAdi: String) {
        val imageUrl = BASE_IMAGE_URL + yemekResimAdi
        
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(imageView)
    }
}
