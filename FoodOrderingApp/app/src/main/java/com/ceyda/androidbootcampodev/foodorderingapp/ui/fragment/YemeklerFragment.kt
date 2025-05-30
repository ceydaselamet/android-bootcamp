package com.ceyda.androidbootcampodev.foodorderingapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceyda.androidbootcampodev.foodorderingapp.R
import com.ceyda.androidbootcampodev.foodorderingapp.databinding.FragmentYemeklerBinding
import com.ceyda.androidbootcampodev.foodorderingapp.ui.adapter.YemeklerAdapter
import com.ceyda.androidbootcampodev.foodorderingapp.ui.viewmodel.YemeklerViewModel
import com.ceyda.androidbootcampodev.foodorderingapp.utils.ImageUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class YemeklerFragment : Fragment() {
    
    private var _binding: FragmentYemeklerBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: YemeklerViewModel by viewModels()
    
    @Inject
    lateinit var imageUtils: ImageUtils
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYemeklerBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Filtreleme butonu için tıklama olayı
        binding.cardViewFilter.setOnClickListener { view ->
            showFilterMenu(view)
        }
        
        // RecyclerView ayarları
        binding.yemeklerRv.layoutManager = GridLayoutManager(requireContext(), 2)
        
        // Yemekleri yükle
        viewModel.tumYemekleriGetir()
        
        // Observe loading state
        viewModel.yukleniyor.observe(viewLifecycleOwner) { yukleniyor ->
            binding.progressBar.visibility = if (yukleniyor) View.VISIBLE else View.GONE
        }
        
        // Observe error state
        viewModel.hata.observe(viewLifecycleOwner) { hata ->
            hata?.let {
                Log.e("YemeklerFragment", "Hata: $it")
                Toast.makeText(requireContext(), "Hata: $it", Toast.LENGTH_LONG).show()
            }
        }
        
        // Observe food list
        viewModel.yemeklerListesi.observe(viewLifecycleOwner) { yemekler ->
            val yemeklerAdapter = YemeklerAdapter(requireContext(), yemekler, viewModel, imageUtils)
            binding.yemeklerRv.adapter = yemeklerAdapter
        }
        
        // Arama kutusu ayarları
        binding.editTextSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.let { viewModel.ara(it) }
            }
            
            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }
    
    private fun showFilterMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
        
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_fiyat_artan -> {
                    viewModel.siralaFiyataGoreArtan()
                    Toast.makeText(requireContext(), "Fiyata göre artan sıralama", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_fiyat_azalan -> {
                    viewModel.siralaFiyataGoreAzalan()
                    Toast.makeText(requireContext(), "Fiyata göre azalan sıralama", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_filtreyi_temizle -> {
                    viewModel.filtreyiTemizle()
                    Toast.makeText(requireContext(), "Filtre temizlendi", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        
        popupMenu.show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
