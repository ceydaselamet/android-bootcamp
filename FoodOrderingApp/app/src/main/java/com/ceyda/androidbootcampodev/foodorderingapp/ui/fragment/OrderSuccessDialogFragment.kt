package com.ceyda.androidbootcampodev.foodorderingapp.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ceyda.androidbootcampodev.foodorderingapp.R
import com.ceyda.androidbootcampodev.foodorderingapp.databinding.FragmentOrderSuccessDialogBinding

class OrderSuccessDialogFragment : DialogFragment() {

    private var _binding: FragmentOrderSuccessDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderSuccessDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Animasyonu başlat
        binding.animationView.setAnimation(R.raw.order_success)
        binding.animationView.playAnimation()

        // Tamam butonuna tıklandığında ana sayfaya dön
        binding.buttonOk.setOnClickListener {
            dismiss()
            // Ana sayfaya dön
            findNavController().navigate(R.id.yemeklerFragment)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "OrderSuccessDialogFragment"
    }
}
