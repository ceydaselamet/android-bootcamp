package com.ceyda.androidbootcampodev.odev5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceyda.androidbootcampodev.odev5.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private var currentInput = ""
    private var sum = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sayı butonları
        val numberButtons = listOf(
            binding.button, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                currentInput += button.text.toString()
                binding.textViewResult.text = currentInput
            }
        }

        // Toplama butonu
        binding.buttonAdd.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                sum += currentInput.toInt()
                currentInput = ""
                binding.textViewResult.text = "0"
            }
        }

        binding.buttonEqual.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                sum += currentInput.toInt()
                binding.textViewResult.text = sum.toString()
                currentInput = ""
                sum = 0
            }
        }

        // Temizleme butonu
        binding.buttonClear.setOnClickListener {
            currentInput = ""
            sum = 0
            binding.textViewResult.text = "0"
        }
    }
}
