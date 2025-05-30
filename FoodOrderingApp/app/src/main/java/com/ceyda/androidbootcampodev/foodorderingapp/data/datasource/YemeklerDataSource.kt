package com.ceyda.androidbootcampodev.foodorderingapp.data.datasource

import android.util.Log
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.CRUDCevap
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.SepetYemek
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.SepetYemeklerCevap
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.Yemekler
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.YemeklerCevap
import com.ceyda.androidbootcampodev.foodorderingapp.data.remote.YemeklerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class YemeklerDataSource @Inject constructor(private val yemeklerApi: YemeklerApi) {
    
    suspend fun tumYemekleriGetir(): List<Yemekler> = withContext(Dispatchers.IO) {
        try {
            val jsonResponse = getJsonResponse("tumYemekleriGetir")
            val yemeklerCevap = YemeklerCevap.fromJson(jsonResponse)
            
            if (yemeklerCevap.success == 1) {
                yemeklerCevap.yemekler
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("YemeklerDataSource", "Yemekleri getirme hatası: ${e.message}")
            emptyList()
        }
    }
    
    suspend fun ara(aramaKelimesi: String): List<Yemekler> = withContext(Dispatchers.IO) {
        try {
            // Şimdilik tüm yemekleri getirip filtreleme yapıyoruz
            // İleride API'de arama endpoint'i eklenirse burada kullanılabilir
            val tumYemekler = tumYemekleriGetir()
            tumYemekler.filter { it.yemek_adi.contains(aramaKelimesi, ignoreCase = true) }
        } catch (e: Exception) {
            Log.e("YemeklerDataSource", "Arama hatası: ${e.message}")
            emptyList()
        }
    }
    
    // Sepete yemek ekleme, sepetten silme gibi işlemler için CRUDCevap kullanılabilir
    // Örnek bir metot:
    suspend fun sepeteEkle(yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String): CRUDCevap = withContext(Dispatchers.IO) {
        try {
            Log.d("YemeklerDataSource", "Sepete ekleniyor: $yemek_adi, $yemek_siparis_adet adet")
            val jsonResponse = getSepeteEkleResponse(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            val crudCevap = CRUDCevap.fromJson(jsonResponse)
            Log.d("YemeklerDataSource", "Sepete ekleme yanıtı: ${crudCevap.success} - ${crudCevap.message}")
            crudCevap
        } catch (e: Exception) {
            Log.e("YemeklerDataSource", "Sepete ekleme hatası: ${e.message}")
            CRUDCevap(0, "Hata: ${e.message}")
        }
    }
    
    suspend fun sepettekiYemekleriGetir(kullanici_adi: String): List<SepetYemek> = withContext(Dispatchers.IO) {
        try {
            Log.d("YemeklerDataSource", "Sepetteki yemekler getiriliyor: $kullanici_adi")
            val jsonResponse = getSepettekiYemeklerResponse(kullanici_adi)
            val sepetYemeklerCevap = SepetYemeklerCevap.fromJson(jsonResponse)
            
            if (sepetYemeklerCevap.success == 1) {
                Log.d("YemeklerDataSource", "${sepetYemeklerCevap.sepet_yemekler.size} adet sepet yemeği alındı")
                sepetYemeklerCevap.sepet_yemekler
            } else {
                Log.d("YemeklerDataSource", "Sepette yemek bulunamadı veya başarısız yanıt")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("YemeklerDataSource", "Sepetteki yemekleri getirme hatası: ${e.message}")
            emptyList()
        }
    }
    
    suspend fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String): CRUDCevap = withContext(Dispatchers.IO) {
        try {
            Log.d("YemeklerDataSource", "Sepetten yemek siliniyor: sepet_yemek_id=$sepet_yemek_id, kullanici_adi=$kullanici_adi")
            val jsonResponse = getSepettenYemekSilResponse(sepet_yemek_id, kullanici_adi)
            val crudCevap = CRUDCevap.fromJson(jsonResponse)
            Log.d("YemeklerDataSource", "Sepetten silme yanıtı: ${crudCevap.success} - ${crudCevap.message}")
            crudCevap
        } catch (e: Exception) {
            Log.e("YemeklerDataSource", "Sepetten yemek silme hatası: ${e.message}")
            CRUDCevap(0, "Hata: ${e.message}")
        }
    }
    
    private suspend fun getJsonResponse(islem: String): String = suspendCoroutine { continuation ->
        yemeklerApi.tumYemekleriGetir().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        try {
                            val responseString = it.string()
                            continuation.resume(responseString)
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    } ?: continuation.resumeWithException(Exception("Response body is null"))
                } else {
                    continuation.resumeWithException(Exception("API error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
    
    private suspend fun getSepeteEkleResponse(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ): String = suspendCoroutine { continuation ->
        yemeklerApi.sepeteYemekEkle(
            yemek_adi = yemek_adi,
            yemek_resim_adi = yemek_resim_adi,
            yemek_fiyat = yemek_fiyat,
            yemek_siparis_adet = yemek_siparis_adet,
            kullanici_adi = kullanici_adi
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        try {
                            val responseString = it.string()
                            Log.d("YemeklerDataSource", "Sepete ekleme ham yanıt: $responseString")
                            continuation.resume(responseString)
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    } ?: continuation.resumeWithException(Exception("Response body is null"))
                } else {
                    continuation.resumeWithException(Exception("API error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("YemeklerDataSource", "Sepete ekleme başarısız: ${t.message}")
                continuation.resumeWithException(t)
            }
        })
    }
    
    private suspend fun getSepettekiYemeklerResponse(kullanici_adi: String): String = suspendCoroutine { continuation ->
        yemeklerApi.sepettekiYemekleriGetir(kullanici_adi).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        try {
                            val responseString = it.string()
                            Log.d("YemeklerDataSource", "Sepetteki yemekler ham yanıt: $responseString")
                            continuation.resume(responseString)
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    } ?: continuation.resumeWithException(Exception("Response body is null"))
                } else {
                    continuation.resumeWithException(Exception("API error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("YemeklerDataSource", "Sepetteki yemekleri getirme başarısız: ${t.message}")
                continuation.resumeWithException(t)
            }
        })
    }
    
    private suspend fun getSepettenYemekSilResponse(sepet_yemek_id: Int, kullanici_adi: String): String = suspendCoroutine { continuation ->
        yemeklerApi.sepettenYemekSil(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        try {
                            val responseString = it.string()
                            Log.d("YemeklerDataSource", "Sepetten yemek silme ham yanıt: $responseString")
                            continuation.resume(responseString)
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    } ?: continuation.resumeWithException(Exception("Response body is null"))
                } else {
                    continuation.resumeWithException(Exception("API error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("YemeklerDataSource", "Sepetten yemek silme başarısız: ${t.message}")
                continuation.resumeWithException(t)
            }
        })
    }
}
