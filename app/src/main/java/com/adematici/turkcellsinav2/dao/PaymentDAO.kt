package com.adematici.turkcellsinav2.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.adematici.turkcellsinav2.model.Payment
import com.adematici.turkcellsinav2.util.Constant.AMOUNT
import com.adematici.turkcellsinav2.util.Constant.DATABASE_NAME
import com.adematici.turkcellsinav2.util.Constant.DATE
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TABLE
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE

class PaymentDAO(context: Context) {

    private var paymentDatabase: SQLiteDatabase? = null
    private var dbOpenHelper: DatabaseOpenHelper =
        DatabaseOpenHelper(context, DATABASE_NAME, null, 1)

    private fun open() {
        paymentDatabase = dbOpenHelper.writableDatabase
    }

    private fun close() {
        if (paymentDatabase != null && paymentDatabase!!.isOpen) {
            paymentDatabase!!.close()
        }
    }

    fun addPayment(payment: Payment) {
        val cv = ContentValues()
        cv.put(AMOUNT, payment.amount)
        cv.put(DATE, payment.date)
        cv.put(PAYMENT_TYPE, payment.paymentType)

        open()
        paymentDatabase!!.insert(PAYMENT_TABLE, null, cv)
        close()
    }

    private fun getAllPaymentQuery(paymentType: Int): Cursor {
        val query = "SELECT * FROM $PAYMENT_TABLE WHERE $PAYMENT_TYPE = ?"
        return paymentDatabase!!.rawQuery(query, arrayOf(paymentType.toString()))
    }

    @SuppressLint("Range")
    fun getAllPayment(paymentType: Int): ArrayList<Payment> {
        val paymentList = ArrayList<Payment>()
        open()

        val c: Cursor = getAllPaymentQuery(paymentType)

        if (c.moveToFirst()) {
            do {
                val payment = Payment(
                    id = c.getInt(0),
                    amount = c.getDouble(c.getColumnIndex(AMOUNT)),
                    date = c.getString(c.getColumnIndex(DATE)),
                    paymentType = c.getInt(c.getColumnIndex(PAYMENT_TYPE))
                )
                paymentList.add(payment)
            } while (c.moveToNext())
        }

        close()
        return paymentList
    }

}