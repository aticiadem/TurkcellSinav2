package com.adematici.turkcellsinav2.presentation.ui.add_payment_type

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adematici.turkcellsinav2.R
import com.adematici.turkcellsinav2.databinding.ActivityAddPaymentTypeBinding
import com.adematici.turkcellsinav2.util.Constant.IS_UPDATE_OR_DELETE

class AddNewPaymentTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPaymentTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.add_new_payment_type)

        val isUpdateOrDelete = intent.getBooleanExtra(IS_UPDATE_OR_DELETE, false)

        initSpinner()
        initButtons(isUpdateOrDelete)
        initClickListeners()
    }

    private fun initSpinner() {
        // todo this variables will come in the database
        val spinnerItems = arrayOf("Odeme Tipi 1", "Odeme Tipi 2")
        val arrayAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            spinnerItems
        )
        binding.spinner.adapter = arrayAdapter
    }

    private fun initButtons(isUpdateOrDelete: Boolean) {
        if (isUpdateOrDelete) {
            binding.buttonUpdate.visibility = View.VISIBLE
            binding.buttonDelete.visibility = View.VISIBLE
            binding.buttonSave.visibility = View.GONE
        } else {
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