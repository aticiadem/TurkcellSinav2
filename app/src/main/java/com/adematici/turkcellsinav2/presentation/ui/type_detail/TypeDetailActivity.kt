package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityTypeDetailBinding
import com.adematici.turkcellsinav2.presentation.adapter.PaymentAdapter
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity
import com.adematici.turkcellsinav2.util.Constant
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM_ID

class TypeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeDetailBinding
    private lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.type_detail)
        val itemId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)

        initRecycler()
        initClickListener(itemId)
    }

    private fun initRecycler() {
        // todo delete mock list
        paymentAdapter = PaymentAdapter()
        //paymentAdapter.paymentList = list
        binding.recyclerView.adapter = paymentAdapter
    }

    private fun initClickListener(id: Int) {
        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, AddNewPaymentTypeActivity::class.java)
            intent.putExtra(Constant.IS_UPDATE_OR_DELETE, true)
            intent.putExtra(PAYMENT_TYPE_ITEM_ID, id)
            startActivity(intent)
        }
        binding.buttonAddPayment.setOnClickListener {
            startActivity(Intent(this, AddPaymentToTypeActivity::class.java))
        }
    }

}