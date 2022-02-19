package com.example.finalproject_devnot.retrofit

import com.example.finalproject_devnot.entity.CRUDCevap
import com.example.finalproject_devnot.entity.Menu
import com.example.finalproject_devnot.entity.Sepet
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface YemeklerDAOInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun getYemekler(): Observable<Menu>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun yemekEkle(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Observable<CRUDCevap>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepetGetir(
        @Field("kullanici_adi") yemek_adi: String
    ): Observable<Sepet>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun yemek_sil(
        @Field("sepet_yemek_id") sepet_yemek_id: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Observable<CRUDCevap>

}

