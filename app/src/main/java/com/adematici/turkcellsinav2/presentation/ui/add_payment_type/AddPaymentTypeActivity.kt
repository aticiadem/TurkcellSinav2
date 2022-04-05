package com.adematici.turkcellsinav2.presentation.ui.add_payment_type

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adematici.turkcellsinav2.databinding.ActivityAddPaymentTypeBinding

class AddPaymentTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}