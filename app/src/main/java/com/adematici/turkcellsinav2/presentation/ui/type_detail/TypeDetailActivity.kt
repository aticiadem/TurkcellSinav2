package com.adematici.turkcellsinav2.presentation.ui.type_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adematici.turkcellsinav2.databinding.ActivityTypeDetailBinding

class TypeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}