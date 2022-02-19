package com.example.finalproject_devnot.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.finalproject_devnot.R
import com.squareup.picasso.Picasso

@BindingAdapter("loadImage")
fun ImageView.loadImage(url:String){
    val base = "http://kasimadalan.pe.hu/yemekler/resimler/"
    Picasso.get()
        .load(base+url)
        .into(this)
}