package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var viewModel: TypeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.type_detail)
        viewModel = ViewModelProvider(this).get(TypeDetailViewModel::class.java)
        val paymentId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)

        initRecycler(paymentId)
        initClickListener(paymentId)
    }

    override fun onResume() {
        super.onResume()
        val paymentId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)
        initRecycler(paymentId)
    }

    private fun initRecycler(paymentId: Int) {
        paymentAdapter = PaymentAdapter()
        paymentAdapter.paymentList = viewModel.getAllPayment(this, paymentId)
        binding.recyclerView.adapter = paymentAdapter
    }

    private fun initClickListener(paymentId: Int) {
        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, AddNewPaymentTypeActivity::class.java)
            intent.putExtra(Constant.IS_UPDATE_OR_DELETE, true)
            intent.putExtra(PAYMENT_TYPE_ITEM_ID, paymentId)
            startActivity(intent)
        }
        binding.buttonAddPayment.setOnClickListener {
            val intent = Intent(this, AddPaymentToTypeActivity::class.java)
            intent.putExtra(PAYMENT_TYPE_ITEM_ID, paymentId)
            startActivity(intent)
        }
    }

}