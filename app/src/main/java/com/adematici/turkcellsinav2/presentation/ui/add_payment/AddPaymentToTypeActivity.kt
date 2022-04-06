package com.adematici.turkcellsinav2.presentation.ui.add_payment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityAddPaymentBinding
import com.adematici.turkcellsinav2.model.Payment
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM_ID
import java.util.*

class AddPaymentToTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentBinding
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var viewModel: AddPaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.add_payment)
        val paymentTypeId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)
        viewModel = ViewModelProvider(this).get(AddPaymentViewModel::class.java)

        initClickListener(paymentTypeId)
        getTheDay()
    }

    private fun initClickListener(paymentType: Int) {
        binding.buttonSave.setOnClickListener {
            if (checkData()) {
                addPaymentToDatabase(paymentType)
            } else {
                Toast.makeText(this, R.string.fill_in_the_blanks, Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonDate.setOnClickListener {
            showCalender()
        }
    }

    private fun addPaymentToDatabase(paymentType: Int) {
        val amount = binding.editTextAmount.text.toString().toDouble()
        val currentDate = binding.buttonDate.text.toString()

        val payment =
            Payment(amount = amount, date = currentDate, paymentType = paymentType)
        viewModel.addPayment(this, payment)
        finish()
    }

    private fun checkData(): Boolean =
        binding.editTextAmount.text.isNotEmpty() && binding.buttonDate.text.isNotEmpty()

    private fun showCalender() {
        val dateListener =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date = "$dayOfMonth.${monthOfYear + 1}.$year"
                binding.buttonDate.text = date
            }

        val datePickerDialog = DatePickerDialog(
            this,
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun getTheDay() {
        val day = calendar[Calendar.DATE]
        val month = calendar[Calendar.MONTH] + 1
        val year = calendar[Calendar.YEAR]
        val date = "$day.${month}.$year"
        binding.buttonDate.text = date
    }

}