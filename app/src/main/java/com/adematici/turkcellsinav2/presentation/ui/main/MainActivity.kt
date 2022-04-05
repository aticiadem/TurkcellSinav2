package com.adematici.turkcellsinav2.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.new_payment_type)
    }
}