package com.ceyda.androidbootcampodev.navigationcomponentkullanimi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ceyda.androidbootcampodev.navigationcomponentkullanimi.databinding.FragmentAyarlarBinding


class AyarlarFragment : Fragment() {

    private lateinit var binding: FragmentAyarlarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAyarlarBinding.inflate(inflater, container, false)

        binding.buttonGoster.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.bottomSheetGecis)
        }
        return binding.root
    }


}