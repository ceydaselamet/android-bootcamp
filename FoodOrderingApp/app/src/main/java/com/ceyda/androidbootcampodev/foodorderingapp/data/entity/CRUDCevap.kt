package com.ceyda.androidbootcampodev.foodorderingapp.data.entity

import android.util.Log
import org.json.JSONObject

data class CRUDCevap(
    val success: Int,
    val message: String
) {
    companion object {
        fun fromJson(jsonString: String): CRUDCevap {
            return try {
                Log.d("CRUDCevap", "Received JSON: $jsonString")
                
                // Parse the JSON string using JSONObject
                val jsonObject = JSONObject(jsonString)
                
                // Get success value
                val success = jsonObject.optInt("success", 0)
                
                // Get message if exists
                val message = if (jsonObject.has("message")) {
                    jsonObject.optString("message", "")
                } else {
                    ""
                }
                
                CRUDCevap(success, message)
            } catch (e: Exception) {
                Log.e("CRUDCevap", "Error parsing response: ${e.message}")
                Log.e("CRUDCevap", "Raw JSON: $jsonString")
                CRUDCevap(0, "Yanıt işleme hatası: ${e.message}")
            }
        }
    }
}
