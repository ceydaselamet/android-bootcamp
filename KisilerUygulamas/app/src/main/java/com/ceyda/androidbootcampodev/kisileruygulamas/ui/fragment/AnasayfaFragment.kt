package com.ceyda.androidbootcampodev.kisileruygulamas.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ceyda.androidbootcampodev.kisileruygulamas.R
import com.ceyda.androidbootcampodev.kisileruygulamas.data.entity.Kisiler
import com.ceyda.androidbootcampodev.kisileruygulamas.databinding.FragmentAnasayfaBinding

class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentAnasayfaBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.kisiKayıtGecis)
        }
        binding.buttonDetay.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.kisiDetayGecis)
        }

        binding.buttonDetay.setOnClickListener {
            val kisi = Kisiler(1, "Ceyda", "0123456")
            val gecis = AnasayfaFragmentDirections.kisiDetayGecis(kisi = kisi)
            Navigation.findNavController(it).navigate(gecis)
        }

        return binding.root
    }


}