package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.adematici.turkcellsinav2.dao.PaymentDAO
import com.adematici.turkcellsinav2.model.Payment

class TypeDetailViewModel: ViewModel() {

    fun getAllPayment(context: Context, paymentId: Int): ArrayList<Payment> {
        return PaymentDAO(context).getAllPayment(paymentId)
    }

}