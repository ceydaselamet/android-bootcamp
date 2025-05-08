package com.ceyda.androidbootcampodev.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ceyda.androidbootcampodev.odev4.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.buttonToA.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.homeToA)
        }

        binding.buttonToX.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.homeToX)
        }

        return binding.root
    }


}