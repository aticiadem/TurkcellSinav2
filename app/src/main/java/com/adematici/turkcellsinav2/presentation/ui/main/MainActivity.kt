package com.adematici.turkcellsinav2.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityMainBinding
import com.adematici.turkcellsinav2.presentation.adapter.PaymentTypesAdapter
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.type_detail.TypeDetailActivity
import com.adematici.turkcellsinav2.util.Constant.IS_UPDATE_OR_DELETE
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM_ID
import com.adematici.turkcellsinav2.util.listener.PaymentTypeClickListener


class MainActivity : AppCompatActivity(), PaymentTypeClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var paymentTypesAdapter: PaymentTypesAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.payment_types)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initView()
        initClickListener()
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView() {
        paymentTypesAdapter = PaymentTypesAdapter(this)
        paymentTypesAdapter.paymentTypes = mainViewModel.getAllPaymentType(this)
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

    override fun onViewClick(id: Int) {
        val intent = Intent(this, TypeDetailActivity::class.java)
        intent.putExtra(PAYMENT_TYPE_ITEM_ID, id)
        startActivity(intent)
    }

}