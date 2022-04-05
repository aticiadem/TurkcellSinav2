package com.adematici.turkcellsinav2.presentation.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.adematici.turkcellsinav2.dao.PaymentTypeDAO
import com.adematici.turkcellsinav2.model.PaymentType

class MainViewModel : ViewModel() {

    fun getAllPaymentType(context: Context): ArrayList<PaymentType> {
        return PaymentTypeDAO(context).getAllPaymentType()
    }

}