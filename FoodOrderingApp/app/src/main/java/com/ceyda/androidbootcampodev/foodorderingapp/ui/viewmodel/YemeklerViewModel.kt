package com.ceyda.androidbootcampodev.foodorderingapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.CRUDCevap
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.SepetYemek
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.Yemekler
import com.ceyda.androidbootcampodev.foodorderingapp.data.repo.YemeklerRepository
import com.ceyda.androidbootcampodev.foodorderingapp.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemeklerViewModel @Inject constructor(
    private val yemeklerRepository: YemeklerRepository,
    private val networkUtils: NetworkUtils
) : ViewModel() {
    
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemek>>()
    var yukleniyor = MutableLiveData<Boolean>(false)
    var hata = MutableLiveData<String?>(null)
    
    init {
        tumYemekleriGetir()
    }
    
    fun tumYemekleriGetir() {
        // Check network connectivity first
        if (!networkUtils.isNetworkAvailable()) {
            hata.value = "İnternet bağlantısı bulunamadı. Lütfen bağlantınızı kontrol edin."
            return
        }
        
        yukleniyor.value = true
        hata.value = null
        
        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.d("YemeklerViewModel", "Yemekleri getirme işlemi başlatıldı")
                val yemekler = yemeklerRepository.tumYemekleriGetir()
                yemeklerListesi.value = yemekler
                Log.d("YemeklerViewModel", "${yemekler.size} adet yemek alındı")
                yukleniyor.value = false
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Hata: ${e.message}")
                hata.value = e.message ?: "Bilinmeyen bir hata oluştu"
                yukleniyor.value = false
            }
        }
    }
    
    fun ara(aramaKelimesi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                yukleniyor.value = true
                hata.value = null
                
                // Repository üzerinden arama işlemini gerçekleştir
                val sonuclar = yemeklerRepository.ara(aramaKelimesi)
                yemeklerListesi.value = sonuclar
                
                yukleniyor.value = false
                Log.d("YemeklerViewModel", "'${aramaKelimesi}' için ${sonuclar.size} sonuç bulundu")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Arama hatası: ${e.message}")
                hata.value = e.message ?: "Arama sırasında bir hata oluştu"
                yukleniyor.value = false
            }
        }
    }
    
    // Fiyata göre artan sıralama
    fun siralaFiyataGoreArtan() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val simdikiListe = yemeklerListesi.value ?: return@launch
                val siraliListe = simdikiListe.sortedBy { it.yemek_fiyat.toInt() }
                yemeklerListesi.value = siraliListe
                Log.d("YemeklerViewModel", "Yemekler fiyata göre artan şekilde sıralandı")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sıralama hatası: ${e.message}")
                hata.value = e.message ?: "Sıralama sırasında bir hata oluştu"
            }
        }
    }
    
    // Fiyata göre azalan sıralama
    fun siralaFiyataGoreAzalan() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val simdikiListe = yemeklerListesi.value ?: return@launch
                val siraliListe = simdikiListe.sortedByDescending { it.yemek_fiyat.toInt() }
                yemeklerListesi.value = siraliListe
                Log.d("YemeklerViewModel", "Yemekler fiyata göre azalan şekilde sıralandı")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sıralama hatası: ${e.message}")
                hata.value = e.message ?: "Sıralama sırasında bir hata oluştu"
            }
        }
    }
    
    // Filtreyi temizleme
    fun filtreyiTemizle() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Tüm yemekleri tekrar getir
                tumYemekleriGetir()
                Log.d("YemeklerViewModel", "Filtre temizlendi, tüm yemekler getirildi")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Filtre temizleme hatası: ${e.message}")
                hata.value = e.message ?: "Filtre temizlenirken bir hata oluştu"
            }
        }
    }
    
    // Sepete yemek ekleme işlevi
    fun sepeteEkle(yemek: Yemekler, adet: Int) {
        val kullanici_adi = "ceyda_selamet" // Sabit kullanıcı adı
        if (!networkUtils.isNetworkAvailable()) {
            hata.value = "İnternet bağlantısı bulunamadı. Lütfen bağlantınızı kontrol edin."
            return
        }
        
        yukleniyor.value = true
        hata.value = null
        
        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.d("YemeklerViewModel", "Sepete ekleme işlemi başlatıldı: ${yemek.yemek_adi}, $adet adet")
                
                val cevap = yemeklerRepository.sepeteEkle(
                    yemek_id = yemek.yemek_id,
                    yemek_adi = yemek.yemek_adi,
                    yemek_resim_adi = yemek.yemek_resim_adi,
                    yemek_fiyat = yemek.yemek_fiyat,
                    yemek_siparis_adet = adet,
                    kullanici_adi = kullanici_adi
                )
                
                yukleniyor.value = false
                
                if (cevap.success == 1) {
                    Log.d("YemeklerViewModel", "Sepete ekleme başarılı: ${cevap.message}")
                    // Sepete ekleme başarılı olduysa sepetteki yemekleri güncelle
                    sepettekiYemekleriGetir()
                } else {
                    Log.e("YemeklerViewModel", "Sepete ekleme başarısız: ${cevap.message}")
                    hata.value = cevap.message
                }
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sepete ekleme hatası: ${e.message}")
                hata.value = e.message ?: "Sepete ekleme sırasında bir hata oluştu"
                yukleniyor.value = false
            }
        }
    }
    
    // Sepetteki yemekleri getirme işlevi
    fun sepettekiYemekleriGetir() {
        val kullanici_adi = "ceyda_selamet" // Sabit kullanıcı adı
        if (!networkUtils.isNetworkAvailable()) {
            hata.value = "İnternet bağlantısı bulunamadı. Lütfen bağlantınızı kontrol edin."
            return
        }
        
        yukleniyor.value = true
        hata.value = null
        
        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.d("YemeklerViewModel", "Sepetteki yemekler getiriliyor")
                
                val sepetYemekler = yemeklerRepository.sepettekiYemekleriGetir(kullanici_adi)
                sepetYemeklerListesi.value = sepetYemekler
                
                yukleniyor.value = false
                Log.d("YemeklerViewModel", "${sepetYemekler.size} adet sepet yemeği alındı")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sepetteki yemekleri getirme hatası: ${e.message}")
                hata.value = e.message ?: "Sepetteki yemekleri getirme sırasında bir hata oluştu"
                yukleniyor.value = false
            }
        }
    }
    
    // Sepetten yemek silme işlevi
    fun sepettenYemekSil(sepet_yemek_id: Int) {
        val kullanici_adi = "ceyda_selamet" // Sabit kullanıcı adı
        if (!networkUtils.isNetworkAvailable()) {
            hata.value = "İnternet bağlantısı bulunamadı. Lütfen bağlantınızı kontrol edin."
            return
        }
        
        yukleniyor.value = true
        hata.value = null
        
        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.d("YemeklerViewModel", "Sepetten yemek silme işlemi başlatıldı: sepet_yemek_id=$sepet_yemek_id")
                
                val cevap = yemeklerRepository.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
                
                yukleniyor.value = false
                
                if (cevap.success == 1) {
                    Log.d("YemeklerViewModel", "Sepetten silme başarılı: ${cevap.message}")
                    // Sepetten silme başarılı olduysa sepetteki yemekleri güncelle
                    sepettekiYemekleriGetir()
                } else {
                    Log.e("YemeklerViewModel", "Sepetten silme başarısız: ${cevap.message}")
                    hata.value = cevap.message
                }
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sepetten yemek silme hatası: ${e.message}")
                hata.value = e.message ?: "Sepetten yemek silme sırasında bir hata oluştu"
                yukleniyor.value = false
            }
        }
    }
}
