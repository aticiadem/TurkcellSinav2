package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityTypeDetailBinding
import com.adematici.turkcellsinav2.model.PaymentType
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity
import com.adematici.turkcellsinav2.util.Constant
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM

class TypeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.type_detail)
        val item = intent.getSerializableExtra(PAYMENT_TYPE_ITEM) as PaymentType?

        initClickListener(item)
    }

    private fun initClickListener(data: PaymentType?) {
        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, AddNewPaymentTypeActivity::class.java)
            intent.putExtra(Constant.IS_UPDATE_OR_DELETE, true)
            intent.putExtra(PAYMENT_TYPE_ITEM, data)
            startActivity(intent)
        }
        binding.buttonAddPayment.setOnClickListener {
            startActivity(Intent(this, AddPaymentToTypeActivity::class.java))
        }
    }

}