package com.ceyda.androidbootcampodev.kisileruygulamas.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ceyda.androidbootcampodev.kisileruygulamas.R
import com.ceyda.androidbootcampodev.kisileruygulamas.databinding.FragmentKisiDetayBinding

class KisiDetayFragment : Fragment() {
   private lateinit var binding : FragmentKisiDetayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentKisiDetayBinding.inflate(inflater, container, false)

        val bundle:KisiDetayFragmentArgs by navArgs()
        val gelen_kisi = bundle.kisi

        binding.editTextKisiAd.setText(gelen_kisi.kisi_ad)
        binding.editTextKisiTel.setText(gelen_kisi.kisi_tel)

        binding.bu

        return binding.root
    }

}