package com.adematici.turkcellsinav2.model

data class Payment(
    val id: Int = 0,
    val amount: Double,
    val date: String,
    val paymentType: Int
)
