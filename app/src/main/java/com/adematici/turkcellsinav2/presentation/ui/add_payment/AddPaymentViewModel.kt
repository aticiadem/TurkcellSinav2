package com.adematici.turkcellsinav2.presentation.ui.add_payment

import android.content.Context
import androidx.lifecycle.ViewModel
import com.adematici.turkcellsinav2.dao.PaymentDAO
import com.adematici.turkcellsinav2.model.Payment

class AddPaymentViewModel : ViewModel() {

    fun addPayment(context: Context, payment: Payment) {
        PaymentDAO(context).addPayment(payment)
    }

}