package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityTypeDetailBinding
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity

class TypeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.type_detail)

        initClickListener()
    }

    private fun initClickListener() {
        binding.buttonEdit.setOnClickListener {
            startActivity(Intent(this, AddNewPaymentTypeActivity::class.java))
        }
        binding.buttonAddPayment.setOnClickListener {
            startActivity(Intent(this, AddPaymentToTypeActivity::class.java))
        }
    }

}