package com.adematici.turkcellsinav2.presentation.ui.add_payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.databinding.ActivityAddPaymentBinding

class AddPaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}