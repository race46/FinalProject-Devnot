package com.example.finalproject_devnot.entity

data class SepetYemekler(
    val kullanici_adi: String,
    val sepet_yemek_id: String,
    val yemek_adi: String,
    var yemek_fiyat: String,
    val yemek_resim_adi: String,
    var yemek_siparis_adet: String
)