package com.adematici.turkcellsinav2.presentation.ui.type_detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.adematici.turkcellsinav2.dao.PaymentDAO
import com.adematici.turkcellsinav2.dao.PaymentTypeDAO
import com.adematici.turkcellsinav2.model.Payment
import com.adematici.turkcellsinav2.model.PaymentType

class TypeDetailViewModel : ViewModel() {

    fun getAllPayment(context: Context, paymentId: Int): ArrayList<Payment> {
        return PaymentDAO(context).getAllPayment(paymentId)
    }

    fun getPaymentType(context: Context, paymentTypeId: Int): PaymentType? {
        return PaymentTypeDAO(context).getSelectedPaymentType(paymentTypeId)
    }

    fun deletePayment(context: Context, paymentId: Int) {
        PaymentDAO(context).deletePayment(paymentId)
    }

}