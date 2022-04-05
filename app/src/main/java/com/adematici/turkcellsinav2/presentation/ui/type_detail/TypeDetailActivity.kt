package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityTypeDetailBinding
import com.adematici.turkcellsinav2.presentation.adapter.PaymentAdapter
import com.adematici.turkcellsinav2.presentation.ui.add_payment.AddPaymentToTypeActivity
import com.adematici.turkcellsinav2.presentation.ui.add_payment_type.AddNewPaymentTypeActivity
import com.adematici.turkcellsinav2.util.Constant
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM_ID
import com.adematici.turkcellsinav2.util.listener.PaymentClickListener

class TypeDetailActivity : AppCompatActivity(), PaymentClickListener {

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
        binding.textViewTitle.text = viewModel.getPaymentType(this, paymentId)!!.title

        initRecycler(paymentId)
        initClickListener(paymentId)
    }

    override fun onResume() {
        super.onResume()
        val paymentId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)
        initRecycler(paymentId)
    }

    private fun initRecycler(paymentId: Int) {
        paymentAdapter = PaymentAdapter(this)
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

    override fun onItemClickListener(itemId: Int) {
        deleteItemAlert(itemId)
    }

    private fun deleteItemAlert(itemId: Int) {
        val adb = AlertDialog.Builder(this)
        adb.setTitle(R.string.delete_data)
        adb.setMessage(R.string.are_you_sure_delete_item)
        adb.setPositiveButton(R.string.yes) { _, _ ->
            viewModel.deletePayment(this, itemId)
            val paymentId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)
            initRecycler(paymentId)
        }
        adb.setNegativeButton(R.string.no) { _, _ -> }
        adb.show()
    }

}