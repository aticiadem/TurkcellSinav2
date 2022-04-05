package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityTypeDetailBinding
import com.adematici.turkcellsinav2.model.Payment
import com.adematici.turkcellsinav2.model.PaymentType
import com.adematici.turkcellsinav2.presentation.adapter.PaymentAdapter
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity
import com.adematici.turkcellsinav2.util.Constant
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM

class TypeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeDetailBinding
    private lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.type_detail)
        val item = intent.getSerializableExtra(PAYMENT_TYPE_ITEM) as PaymentType?

        initRecycler()
        initClickListener(item)
    }

    private fun initRecycler() {
        // todo delete mock list
        val list: ArrayList<Payment> = arrayListOf(
            Payment(0, 343.63, "25.01.2022"),
            Payment(1, 5423.46, "22.02.2022"),
            Payment(2, 2453.00, "23.03.2022"),
            Payment(3, 343.23, "01.01.2022"),
            Payment(4, 6463.63, "22.01.2022")
        )
        paymentAdapter = PaymentAdapter()
        paymentAdapter.paymentList = list
        binding.recyclerView.adapter = paymentAdapter
    }

    private fun initClickListener(item: PaymentType?) {
        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, AddNewPaymentTypeActivity::class.java)
            intent.putExtra(Constant.IS_UPDATE_OR_DELETE, true)
            intent.putExtra(PAYMENT_TYPE_ITEM, item)
            startActivity(intent)
        }
        binding.buttonAddPayment.setOnClickListener {
            startActivity(Intent(this, AddPaymentToTypeActivity::class.java))
        }
    }

}