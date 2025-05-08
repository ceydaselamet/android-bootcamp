package com.ceyda.androidbootcampodev.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ceyda.androidbootcampodev.odev4.databinding.FragmentHomeBinding
import com.ceyda.androidbootcampodev.odev4.databinding.FragmentXBinding

class XFragment : Fragment() {
    private lateinit var binding: FragmentXBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXBinding.inflate(inflater, container, false)

        binding.buttonXToY.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.xToY)
        }

        return binding.root
    }


}