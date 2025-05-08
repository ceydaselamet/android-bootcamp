package com.ceyda.androidbootcampodev.calismayapisi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ceyda.androidbootcampodev.calismayapisi.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gelenMesaj = intent.getStringExtra("mesaj")

        binding.textViewSonuc.text = gelenMesaj
    }
}