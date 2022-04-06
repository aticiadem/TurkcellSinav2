package com.adematici.turkcellsinav2.presentation.ui.add_payment_type

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityAddPaymentTypeBinding
import com.adematici.turkcellsinav2.model.PaymentType
import com.adematici.turkcellsinav2.presentation.ui.main.MainActivity
import com.adematici.turkcellsinav2.util.Constant.IS_UPDATE_OR_DELETE
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_ITEM_ID
import com.adematici.turkcellsinav2.util.Period
import com.adematici.turkcellsinav2.util.Period.Companion.getAllPeriods

class AddNewPaymentTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentTypeBinding
    private lateinit var viewModel: AddNewPaymentTypeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isUpdateOrDelete = intent.getBooleanExtra(IS_UPDATE_OR_DELETE, false)
        val itemId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)
        viewModel = ViewModelProvider(this).get(AddNewPaymentTypeViewModel::class.java)

        initSpinner()
        initView(isUpdateOrDelete, itemId)
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

    private fun initView(isUpdateOrDelete: Boolean, itemId: Int) {
        if (isUpdateOrDelete) {
            title = getString(R.string.update_payment_type)

            val item: PaymentType = viewModel.getSelectedPaymentType(this, itemId)

            binding.editTextPaymentTitle.setText(item.title)
            item.periodDay?.let {
                binding.editTextPeriodDay.setText(it.toString())
            }

            binding.apply {
                buttonUpdate.visibility = View.VISIBLE
                buttonDelete.visibility = View.VISIBLE
                buttonSave.visibility = View.GONE
            }
        } else {
            title = getString(R.string.add_new_payment_type)
            binding.apply {
                buttonUpdate.visibility = View.GONE
                buttonDelete.visibility = View.GONE
                buttonSave.visibility = View.VISIBLE
            }
        }
    }

    private fun initClickListeners() {
        val itemId = intent.getIntExtra(PAYMENT_TYPE_ITEM_ID, 0)
        binding.buttonSave.setOnClickListener {
            saveDataToDatabase()
        }
        binding.buttonUpdate.setOnClickListener {
            updateData(itemId)
        }
        binding.buttonDelete.setOnClickListener {
            deleteData(itemId)
        }
    }

    private fun saveDataToDatabase() {
        val title = binding.editTextPaymentTitle.text.toString()
        val periodListItem = binding.spinner.selectedItem.toString()
        if (title.isEmpty()) {
            Toast.makeText(this, R.string.title_cannot_be_empty, Toast.LENGTH_LONG).show()
        } else {
            val periodDay = binding.editTextPeriodDay.text.toString()
            if (binding.editTextPeriodDay.text.isNullOrEmpty()) {
                val paymentType =
                    PaymentType(title = title, period = periodListItem, periodDay = null)
                viewModel.addNewPaymentType(this, paymentType)
                finish()
            } else {
                checkCalenderData(null, title, periodListItem, periodDay.toInt(), false)
            }
        }
    }

    private fun updateData(id: Int) {
        val title = binding.editTextPaymentTitle.text.toString()

        if (title.isEmpty()) {
            Toast.makeText(this, R.string.title_cannot_be_empty, Toast.LENGTH_LONG).show()
        } else {
            val periodDay = binding.editTextPeriodDay.text.toString()
            val periodListItem = binding.spinner.selectedItem.toString()
            if (!binding.editTextPeriodDay.text.isNullOrEmpty()) {
                checkCalenderData(id = id, title, periodListItem, periodDay.toInt(), true)
            } else {
                viewModel.updatePaymentType(this, id, title, periodListItem, null)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun checkCalenderData(
        id: Int?,
        title: String,
        periodListItem: String,
        periodDay: Int,
        isUpdate: Boolean,
    ) {
        if (
            (periodListItem == Period.WEEK.periodName && periodDay > Period.WEEK.periodMaxValue) ||
            (periodListItem == Period.MONTH.periodName && periodDay > Period.MONTH.periodMaxValue) ||
            (periodListItem == Period.YEAR.periodName && periodDay > Period.YEAR.periodMaxValue)
        ) {
            Toast.makeText(this, R.string.enter_a_valid_day, Toast.LENGTH_LONG).show()
        } else {
            if (isUpdate) {
                viewModel.updatePaymentType(this, id!!, title, periodListItem, periodDay)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                val paymentType =
                    PaymentType(title = title, period = periodListItem, periodDay = periodDay)
                viewModel.addNewPaymentType(this, paymentType)
            }
            finish()
        }
    }

    private fun deleteData(id: Int) {
        val adb = AlertDialog.Builder(this)
        adb.setTitle(R.string.delete_data)
        adb.setMessage(R.string.are_you_sure_delete_item)
        adb.setPositiveButton(R.string.yes) { _, _ ->
            viewModel.deletePaymentType(this, id)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        adb.setNegativeButton(R.string.no) { _, _ -> }
        adb.show()
    }

}