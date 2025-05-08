package com.ceyda.androidbootcampodev.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.ceyda.androidbootcampodev.odev4.databinding.FragmentYBinding


class YFragment : Fragment() {
    private lateinit var binding: FragmentYBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYBinding.inflate(inflater, container, false)
        
        val geriTusu = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack(R.id.homeFragment, false)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, geriTusu)
        
        return binding.root
    }


}