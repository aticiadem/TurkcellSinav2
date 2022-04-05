package com.adematici.turkcellsinav2.util

enum class Period(val periodName: String, val periodMaxValue: Int) {
    WEEK("Haftalık", 7),
    MONTH("Aylık", 30),
    YEAR("Yıllık", 365);

    companion object {
        fun getAllPeriods(): List<String> {
            val list: List<String> = values().map {
                it.periodName
            }
            return list
        }
    }
}