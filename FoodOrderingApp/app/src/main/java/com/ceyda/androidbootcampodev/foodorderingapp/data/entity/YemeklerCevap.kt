package com.ceyda.androidbootcampodev.foodorderingapp.data.entity

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import org.json.JSONObject

data class YemeklerCevap(
    val yemekler: List<Yemekler>,
    val success: Int
) {
    companion object {
        fun fromJson(jsonString: String): YemeklerCevap {
            return try {
                Log.d("YemeklerCevap", "Received JSON: $jsonString")
                
                // Parse the JSON string using JSONObject
                val jsonObject = JSONObject(jsonString)
                
                // Get success value
                val success = jsonObject.optInt("success", 0)
                
                // Get yemekler array if success is 1
                val yemeklerList = if (success == 1 && jsonObject.has("yemekler")) {
                    val yemeklerArray = jsonObject.getJSONArray("yemekler")
                    val gson = Gson()
                    val yemekler = mutableListOf<Yemekler>()
                    
                    for (i in 0 until yemeklerArray.length()) {
                        val yemekJson = yemeklerArray.getJSONObject(i)
                        val yemek = Yemekler(
                            yemek_id = yemekJson.optInt("yemek_id"),
                            yemek_adi = yemekJson.optString("yemek_adi"),
                            yemek_resim_adi = yemekJson.optString("yemek_resim_adi"),
                            yemek_fiyat = yemekJson.optInt("yemek_fiyat")
                        )
                        yemekler.add(yemek)
                    }
                    yemekler
                } else {
                    emptyList()
                }
                
                YemeklerCevap(yemeklerList, success)
            } catch (e: JsonSyntaxException) {
                Log.e("YemeklerCevap", "JSON parsing error: ${e.message}")
                Log.e("YemeklerCevap", "Raw JSON: $jsonString")
                YemeklerCevap(emptyList(), 0)
            } catch (e: Exception) {
                Log.e("YemeklerCevap", "Error parsing response: ${e.message}")
                YemeklerCevap(emptyList(), 0)
            }
        }
    }
}
