package com.ceyda.androidbootcampodev.foodorderingapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.Yemekler
import com.ceyda.androidbootcampodev.foodorderingapp.databinding.CardYemekBinding
import com.ceyda.androidbootcampodev.foodorderingapp.ui.viewmodel.YemeklerViewModel
import com.ceyda.androidbootcampodev.foodorderingapp.utils.ImageUtils

class YemeklerAdapter(
    private val mContext: Context,
    private val yemeklerListesi: List<Yemekler>,
    private val viewModel: YemeklerViewModel,
    private val imageUtils: ImageUtils
) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(val binding: CardYemekBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardYemekBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val binding = holder.binding
        val yemek = yemeklerListesi[position]
        
        binding.textViewYemekAdi.text = yemek.yemek_adi
        binding.textViewYemekFiyat.text = "${yemek.yemek_fiyat} ₺"
        
        // Yemek resmini yükle
        imageUtils.loadYemekImage(binding.imageViewYemek, yemek.yemek_resim_adi)
        
        // Sepete ekle butonu kaldırıldı
        
        binding.imageViewSil.setOnClickListener {
            // Silme işlemi için viewModel'deki bir metodu çağırabiliriz
            // viewModel.sil(yemek.yemek_id)
        }
        
        // Karta tıklandığında detay sayfasına geçiş yap
        binding.root.setOnClickListener {
            // Detay sayfasına geçiş için Navigation kullan
            val action = com.ceyda.androidbootcampodev.foodorderingapp.ui.fragment.YemeklerFragmentDirections.actionYemeklerFragmentToYemekDetayFragment(yemek)
            androidx.navigation.Navigation.findNavController(it).navigate(action)
        }
    }
}
