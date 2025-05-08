package com.ceyda.androidbootcampodev.widgetskullanimi

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ceyda.androidbootcampodev.widgetskullanimi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var kontrol = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            kontrol = isChecked
            if(kontrol) {
                val secilenButon = findViewById<Button>(checkedId)
                val butonYazi = secilenButon.text.toString()

                Log.e("Sonuc", butonYazi)
            }

        }

        val ulkeler = ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İtalya")
        ulkeler.add("Japonya")

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, ulkeler)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)


        binding.buttonGoster.setOnClickListener {
            if(kontrol) {
                val secilenButon = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val butonYazi = secilenButon.text.toString()

                Log.e("Sonuc (Goster) ", butonYazi)
            }

            val secilenUlke= binding.autoCompleteTextView.text.toString()
            Log.e("Sonuc (Ulke) ", secilenUlke)
        }
    }
}