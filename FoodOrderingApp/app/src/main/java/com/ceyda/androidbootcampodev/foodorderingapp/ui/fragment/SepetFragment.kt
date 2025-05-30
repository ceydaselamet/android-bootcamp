package com.ceyda.androidbootcampodev.foodorderingapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceyda.androidbootcampodev.foodorderingapp.databinding.FragmentSepetBinding
import com.ceyda.androidbootcampodev.foodorderingapp.ui.adapter.SepetYemeklerAdapter
import com.ceyda.androidbootcampodev.foodorderingapp.ui.viewmodel.YemeklerViewModel
import com.ceyda.androidbootcampodev.foodorderingapp.utils.ImageUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SepetFragment : Fragment() {
    
    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: YemeklerViewModel by viewModels()
    
    @Inject
    lateinit var imageUtils: ImageUtils
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // RecyclerView ayarları
        binding.recyclerViewSepet.layoutManager = LinearLayoutManager(requireContext())
        
        // Sepetteki yemekleri getir
        viewModel.sepettekiYemekleriGetir()
        
        // LiveData gözlemleme
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) { sepetYemekler ->
            if (sepetYemekler.isNotEmpty()) {
                // Sepet dolu
                binding.textViewBosSepeUyari.visibility = View.GONE
                binding.recyclerViewSepet.visibility = View.VISIBLE
                binding.textViewToplamTutar.visibility = View.VISIBLE
                binding.buttonSiparisVer.visibility = View.VISIBLE
                
                // Adapter oluştur ve RecyclerView'a ata
                val adapter = SepetYemeklerAdapter(requireContext(), sepetYemekler, viewModel, imageUtils)
                binding.recyclerViewSepet.adapter = adapter
                
                // Toplam tutarı hesapla
                val toplamTutar = sepetYemekler.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
                binding.textViewToplamTutar.text = "Toplam Tutar: $toplamTutar ₺"
                
                Log.d("SepetFragment", "${sepetYemekler.size} adet sepet yemeği görüntüleniyor")
            } else {
                // Sepet boş
                binding.textViewBosSepeUyari.visibility = View.VISIBLE
                binding.recyclerViewSepet.visibility = View.GONE
                binding.textViewToplamTutar.visibility = View.GONE
                binding.buttonSiparisVer.visibility = View.GONE
                
                Log.d("SepetFragment", "Sepet boş")
            }
        }
        
        // Yükleniyor durumu
        viewModel.yukleniyor.observe(viewLifecycleOwner) { yukleniyor ->
            // Yükleniyor göstergesi eklenebilir
        }
        
        // Hata durumu
        viewModel.hata.observe(viewLifecycleOwner) { hata ->
            hata?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                Log.e("SepetFragment", "Hata: $it")
            }
        }
        
        // Sipariş ver butonu
        binding.buttonSiparisVer.setOnClickListener {
            // Sipariş başarılı dialog'unu göster
            showOrderSuccessDialog()
            
            // Sepeti temizle
            viewModel.sepetYemeklerListesi.value?.forEach { sepetYemek ->
                viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id)
            }
        }
    }
    
    private fun showOrderSuccessDialog() {
        val dialogFragment = OrderSuccessDialogFragment()
        dialogFragment.show(parentFragmentManager, OrderSuccessDialogFragment.TAG)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
