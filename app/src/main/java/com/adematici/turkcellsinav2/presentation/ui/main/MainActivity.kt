package com.adematici.turkcellsinav2.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityMainBinding
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity
import com.adematici.turkcellsinav2.util.Constant.IS_UPDATE_OR_DELETE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.payment_types)

        initClickListener()
    }

    private fun initClickListener() {
        binding.buttonAddNewPaymentType.setOnClickListener {
            val intent = Intent(this, AddPaymentToTypeActivity::class.java)
            intent.putExtra(IS_UPDATE_OR_DELETE, false)
            startActivity(intent)
        }
    }

}