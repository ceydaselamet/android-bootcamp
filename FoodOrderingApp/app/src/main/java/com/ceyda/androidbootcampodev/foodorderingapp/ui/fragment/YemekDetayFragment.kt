package com.ceyda.androidbootcampodev.foodorderingapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ceyda.androidbootcampodev.foodorderingapp.data.entity.Yemekler
import com.ceyda.androidbootcampodev.foodorderingapp.databinding.FragmentYemekDetayBinding
import com.ceyda.androidbootcampodev.foodorderingapp.ui.viewmodel.YemeklerViewModel
import com.ceyda.androidbootcampodev.foodorderingapp.utils.ImageUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    
    private var _binding: FragmentYemekDetayBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: YemeklerViewModel by viewModels()
    
    @Inject
    lateinit var imageUtils: ImageUtils
    
    private var adet = 1
    private lateinit var yemek: Yemekler
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYemekDetayBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Argümanları al
        arguments?.let {
            if (it.containsKey("yemek")) {
                yemek = it.getSerializable("yemek") as Yemekler
                
                // Yemek bilgilerini göster
                binding.textViewYemekAdi.text = yemek.yemek_adi
                binding.textViewYemekFiyat.text = "₺${yemek.yemek_fiyat}"
                binding.textViewYemekAciklama.text = "Bu lezzetli ${yemek.yemek_adi} özenle hazırlanmıştır. Deneyen herkes tarafından beğenilmektedir."
                
                // Yemek resmini yükle
                imageUtils.loadYemekImage(binding.imageViewYemekDetay, yemek.yemek_resim_adi)
            }
        }
        
        // Adet kontrolü
        binding.textViewAdetSayisi.text = adet.toString()
        
        binding.buttonAzalt.setOnClickListener {
            if (adet > 1) {
                adet--
                binding.textViewAdetSayisi.text = adet.toString()
            }
        }
        
        binding.buttonArttir.setOnClickListener {
            adet++
            binding.textViewAdetSayisi.text = adet.toString()
        }
        
        // Sepete ekle butonu
        binding.buttonSepeteEkle.setOnClickListener {
            viewModel.sepeteEkle(yemek, adet)
            Toast.makeText(requireContext(), "${adet} adet ${yemek.yemek_adi} sepete eklendi", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        
        // Geri butonu
        binding.buttonGeri.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
