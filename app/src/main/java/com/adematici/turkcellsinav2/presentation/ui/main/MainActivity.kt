package com.adematici.turkcellsinav2.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityMainBinding
import com.adematici.turkcellsinav2.model.PaymentType
import com.adematici.turkcellsinav2.presentation.adapter.PaymentTypesAdapter
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.type_detail.TypeDetailActivity
import com.adematici.turkcellsinav2.util.Constant.IS_UPDATE_OR_DELETE
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM
import com.adematici.turkcellsinav2.util.Period
import com.adematici.turkcellsinav2.util.listener.PaymentTypeClickListener

class MainActivity : AppCompatActivity(), PaymentTypeClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var paymentTypesAdapter: PaymentTypesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.payment_types)

        initView()
        initClickListener()
    }

    private fun initView() {
        // todo delete mock list
        val list: ArrayList<PaymentType> = arrayListOf(
            PaymentType(0, "Payment 1", null, null),
            PaymentType(2, "Payment 2", Period.MONTH.periodName, 23),
            PaymentType(3, "Payment 3", Period.MONTH.periodName, 2),
            PaymentType(4, "Payment 4", Period.WEEK.periodName, 3),
            PaymentType(5, "Payment 5", Period.YEAR.periodName, 233)
        )
        paymentTypesAdapter = PaymentTypesAdapter(this)
        paymentTypesAdapter.paymentTypes = list
        binding.recyclerView.adapter = paymentTypesAdapter
    }

    private fun initClickListener() {
        binding.buttonAddNewPaymentType.setOnClickListener {
            val intent = Intent(this, AddNewPaymentTypeActivity::class.java)
            intent.putExtra(IS_UPDATE_OR_DELETE, false)
            startActivity(intent)
        }
    }

    override fun onItemClick() {
        val intent = Intent(this, AddPaymentToTypeActivity::class.java)
        startActivity(intent)
    }

    override fun onViewClick(data: PaymentType) {
        val intent = Intent(this, TypeDetailActivity::class.java)
        intent.putExtra(PAYMENT_TYPE_ITEM, data)
        startActivity(intent)
    }

}