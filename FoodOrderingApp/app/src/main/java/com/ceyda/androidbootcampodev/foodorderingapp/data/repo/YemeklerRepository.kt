package com.ceyda.androidbootcampodev.foodorderingapp.data.repo

import com.ceyda.androidbootcampodev.foodorderingapp.data.datasource.YemeklerDataSource
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.CRUDCevap
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.SepetYemek
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.Yemekler
import javax.inject.Inject

class YemeklerRepository @Inject constructor(private val yemeklerDataSource: YemeklerDataSource) {
    
    suspend fun tumYemekleriGetir(): List<Yemekler> = yemeklerDataSource.tumYemekleriGetir()
    
    suspend fun ara(aramaKelimesi: String): List<Yemekler> = yemeklerDataSource.ara(aramaKelimesi)
    
    suspend fun sepeteEkle(yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String): CRUDCevap = 
        yemeklerDataSource.sepeteEkle(yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        
    suspend fun sepettekiYemekleriGetir(kullanici_adi: String): List<SepetYemek> =
        yemeklerDataSource.sepettekiYemekleriGetir(kullanici_adi)
        
    suspend fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String): CRUDCevap =
        yemeklerDataSource.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
}
