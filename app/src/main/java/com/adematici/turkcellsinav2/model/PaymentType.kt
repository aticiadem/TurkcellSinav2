package com.adematici.turkcellsinav2.model

data class PaymentType(
    val id: Int = 0,
    var title: String,
    var period: String?,
    var periodDay: Int?,
)
