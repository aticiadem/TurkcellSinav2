package com.adematici.turkcellsinav2.presentation.ui.add_payment_type

import android.content.Context
import androidx.lifecycle.ViewModel
import com.adematici.turkcellsinav2.dao.PaymentTypeDAO
import com.adematici.turkcellsinav2.model.PaymentType

class AddNewPaymentTypeViewModel : ViewModel() {

    fun addNewPaymentType(context: Context, paymentType: PaymentType) {
        PaymentTypeDAO(context).addPaymentType(paymentType)
    }

    fun getSelectedPaymentType(context: Context, id: Int): PaymentType {
        return PaymentTypeDAO(context).getSelectedPaymentType(id)!!
    }

    fun updatePaymentType(
        context: Context,
        id: Int,
        title: String,
        period: String?,
        periodDay: Int?,
    ) {
        PaymentTypeDAO(context).updatePaymentType(id, title, period, periodDay)
    }

    fun deletePaymentType(context: Context, id: Int) {
        PaymentTypeDAO(context).deletePaymentType(id)
    }

}