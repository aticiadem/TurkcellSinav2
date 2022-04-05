package com.adematici.turkcellsinav2.presentation.ui.add_payment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.databinding.ActivityAddPaymentBinding
import java.util.*


class AddPaymentToTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentBinding
    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListener()
        getTheDay()
    }

    private fun getTheDay() {
        val day = calendar[Calendar.DATE]
        val month = calendar[Calendar.MONTH] + 1
        val year = calendar[Calendar.YEAR]
        val date = "$day-${month}-$year"
        binding.buttonDate.text = date
    }

    private fun initClickListener() {
        binding.buttonSave.setOnClickListener {
            // todo we will update this function
        }

        binding.buttonDate.setOnClickListener {
            showCalender()
        }
    }

    private fun showCalender() {
        val dateListener =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date = "$dayOfMonth-${monthOfYear + 1}-$year"
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

}