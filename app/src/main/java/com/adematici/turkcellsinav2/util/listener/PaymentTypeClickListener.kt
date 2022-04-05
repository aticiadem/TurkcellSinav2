package com.adematici.turkcellsinav2.util.listener

import com.adematici.turkcellsinav2.model.PaymentType

interface PaymentTypeClickListener {
    fun onItemClick()
    fun onViewClick(data: PaymentType)
}