package com.ceyda.androidbootcampodev.odev5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ceyda.androidbootcampodev.odev5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Fragment is automatically loaded from the layout XML
        // via the FragmentContainerView with android:name attribute
    }
}