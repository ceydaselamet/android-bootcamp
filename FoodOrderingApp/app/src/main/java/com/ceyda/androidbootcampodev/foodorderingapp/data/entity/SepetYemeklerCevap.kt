package com.ceyda.androidbootcampodev.foodorderingapp.data.entity

import android.util.Log
import org.json.JSONObject

data class SepetYemeklerCevap(
    val sepet_yemekler: List<SepetYemek>,
    val success: Int
) {
    companion object {
        fun fromJson(jsonString: String): SepetYemeklerCevap {
            return try {
                Log.d("SepetYemeklerCevap", "Received JSON: $jsonString")
                
                // Parse the JSON string using JSONObject
                val jsonObject = JSONObject(jsonString)
                
                // Get success value
                val success = jsonObject.optInt("success", 0)
                
                // Get sepet_yemekler array if success is 1
                val sepetYemeklerList = if (success == 1 && jsonObject.has("sepet_yemekler")) {
                    val sepetYemeklerArray = jsonObject.getJSONArray("sepet_yemekler")
                    val sepetYemekler = mutableListOf<SepetYemek>()
                    
                    for (i in 0 until sepetYemeklerArray.length()) {
                        val sepetYemekJson = sepetYemeklerArray.getJSONObject(i)
                        val sepetYemek = SepetYemek(
                            sepet_yemek_id = sepetYemekJson.optInt("sepet_yemek_id"),
                            yemek_adi = sepetYemekJson.optString("yemek_adi"),
                            yemek_resim_adi = sepetYemekJson.optString("yemek_resim_adi"),
                            yemek_fiyat = sepetYemekJson.optInt("yemek_fiyat"),
                            yemek_siparis_adet = sepetYemekJson.optInt("yemek_siparis_adet"),
                            kullanici_adi = sepetYemekJson.optString("kullanici_adi")
                        )
                        sepetYemekler.add(sepetYemek)
                    }
                    sepetYemekler
                } else {
                    emptyList()
                }
                
                SepetYemeklerCevap(sepetYemeklerList, success)
            } catch (e: Exception) {
                Log.e("SepetYemeklerCevap", "Error parsing response: ${e.message}")
                Log.e("SepetYemeklerCevap", "Raw JSON: $jsonString")
                SepetYemeklerCevap(emptyList(), 0)
            }
        }
    }
}
