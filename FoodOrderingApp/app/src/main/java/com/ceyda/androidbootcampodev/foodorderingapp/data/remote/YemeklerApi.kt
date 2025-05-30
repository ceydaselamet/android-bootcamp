package com.ceyda.androidbootcampodev.foodorderingapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.Call

interface YemeklerApi {
    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekleriGetir(): Call<ResponseBody>
    
    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteYemekEkle(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Call<ResponseBody>
    
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullanici_adi: String
    ): Call<ResponseBody>
    
    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepet_yemek_id: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Call<ResponseBody>
}
