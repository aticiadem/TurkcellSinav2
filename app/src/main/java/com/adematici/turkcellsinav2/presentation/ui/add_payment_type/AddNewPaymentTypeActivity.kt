package com.adematici.turkcellsinav2.presentation.ui.add_payment_type

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityAddPaymentTypeBinding
import com.adematici.turkcellsinav2.model.PaymentType
import com.adematici.turkcellsinav2.util.Constant.IS_UPDATE_OR_DELETE
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM
import com.adematici.turkcellsinav2.util.Period.Companion.getAllPeriods

class AddNewPaymentTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isUpdateOrDelete = intent.getBooleanExtra(IS_UPDATE_OR_DELETE, false)
        val item = intent.getSerializableExtra(PAYMENT_TYPE_ITEM) as PaymentType?

        initSpinner()
        initView(isUpdateOrDelete, item)
        initClickListeners()
    }

    private fun initSpinner() {
        val spinnerItems = getAllPeriods()
        val arrayAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            spinnerItems
        )
        binding.spinner.adapter = arrayAdapter
    }

    private fun initView(isUpdateOrDelete: Boolean, item: PaymentType?) {
        if (isUpdateOrDelete) {
            title = getString(R.string.update_payment_type)

            binding.editTextPaymentTitle.setText(item!!.title)
            item.periodDay?.let {
                binding.editTextPeriodDay.setText(it.toString())
            }

            binding.buttonUpdate.visibility = View.VISIBLE
            binding.buttonDelete.visibility = View.VISIBLE
            binding.buttonSave.visibility = View.GONE
        } else {
            title = getString(R.string.add_new_payment_type)
            binding.buttonUpdate.visibility = View.GONE
            binding.buttonDelete.visibility = View.GONE
            binding.buttonSave.visibility = View.VISIBLE
        }
    }

    private fun initClickListeners() {
        binding.buttonSave.setOnClickListener {
            saveDataToDatabase()
        }
        binding.buttonUpdate.setOnClickListener {
            // todo update data int the database
        }
        binding.buttonDelete.setOnClickListener {
            // todo show popup and delete
        }
    }

    private fun saveDataToDatabase() {
        val title = binding.editTextPaymentTitle.text.toString()
        val periodListItem = binding.spinner.selectedItem.toString()
        val periodDay = binding.editTextPeriodDay.text.toString()

        if (title.isEmpty()) {
            Toast.makeText(this, R.string.title_cannot_be_empty, Toast.LENGTH_LONG).show()
        } else {
            // todo add data to the database
        }
    }

}