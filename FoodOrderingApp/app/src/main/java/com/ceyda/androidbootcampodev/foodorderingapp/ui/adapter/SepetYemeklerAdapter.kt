package com.ceyda.androidbootcampodev.foodorderingapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.SepetYemek
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.Yemekler
import com.ceyda.androidbootcampodev.foodorderingapp.databinding.CardSepetYemekBinding
import com.ceyda.androidbootcampodev.foodorderingapp.ui.viewmodel.YemeklerViewModel
import com.ceyda.androidbootcampodev.foodorderingapp.utils.ImageUtils

class SepetYemeklerAdapter(
    private val mContext: Context,
    private val sepetYemeklerListesi: List<SepetYemek>,
    private val viewModel: YemeklerViewModel,
    private val imageUtils: ImageUtils
) : RecyclerView.Adapter<SepetYemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(val binding: CardSepetYemekBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardSepetYemekBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return sepetYemeklerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val sepetYemek = sepetYemeklerListesi[position]
        val binding = holder.binding

        binding.textViewSepetYemekAdi.text = sepetYemek.yemek_adi
        binding.textViewSepetYemekFiyat.text = "${sepetYemek.yemek_fiyat} ₺"
        binding.textViewSepetYemekAdet.text = "${sepetYemek.yemek_siparis_adet}"

        // Adet azaltma butonu
        binding.buttonDecrease.setOnClickListener {
            if (sepetYemek.yemek_siparis_adet > 1) {
                val yeniAdet = sepetYemek.yemek_siparis_adet - 1
                viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id)
                viewModel.sepeteEkle(
                    Yemekler(
                        yemek_id = 0,
                        yemek_adi = sepetYemek.yemek_adi,
                        yemek_resim_adi = sepetYemek.yemek_resim_adi,
                        yemek_fiyat = sepetYemek.yemek_fiyat
                    ),
                    yeniAdet
                )
            }
        }

        // Adet artırma butonu
        binding.buttonIncrease.setOnClickListener {
            val yeniAdet = sepetYemek.yemek_siparis_adet + 1
            viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id)
            viewModel.sepeteEkle(
                Yemekler(
                    yemek_id = 0,
                    yemek_adi = sepetYemek.yemek_adi,
                    yemek_resim_adi = sepetYemek.yemek_resim_adi,
                    yemek_fiyat = sepetYemek.yemek_fiyat
                ),
                yeniAdet
            )
        }
        
        // Yemek resmini yükle
        imageUtils.loadYemekImage(binding.imageViewSepetYemek, sepetYemek.yemek_resim_adi)
        
        // Sepetten silme işlemi için
        binding.imageViewSepetSil.setOnClickListener {
            // Silme işlemini viewModel üzerinden gerçekleştir
            viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id)
            android.widget.Toast.makeText(mContext, "${sepetYemek.yemek_adi} sepetten çıkarıldı", android.widget.Toast.LENGTH_SHORT).show()
        }
        
        // Sepetteki ürüne tıklandığında detay sayfasına git
        binding.cardViewSepetYemek.setOnClickListener { view ->
            // Sepetteki yemek bilgilerinden Yemekler nesnesine dönüştür
            val yemek = Yemekler(
                yemek_id = 0, // API'den gelen sepet verisinde yemek_id olmadığı için varsayılan değer
                yemek_adi = sepetYemek.yemek_adi,
                yemek_resim_adi = sepetYemek.yemek_resim_adi,
                yemek_fiyat = sepetYemek.yemek_fiyat
            )
            
            // Detay sayfasına geçiş yap
            val bundle = androidx.core.os.bundleOf("yemek" to yemek)
            Navigation.findNavController(view).navigate(com.ceyda.androidbootcampodev.foodorderingapp.R.id.sepetToYemekDetay, bundle)
        }
    }
}
