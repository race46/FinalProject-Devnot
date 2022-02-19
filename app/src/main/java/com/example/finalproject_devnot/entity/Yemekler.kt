package com.example.finalproject_devnot.entity

import android.os.Parcelable

@kotlinx.android.parcel.Parcelize
data class Yemekler(
    val yemek_adi: String,
    val yemek_fiyat: String,
    val yemek_id: String,
    val yemek_resim_adi: String
):Parcelable