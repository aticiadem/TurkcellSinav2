package com.adematici.turkcellsinav2.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.adematici.turkcellsinav2.model.PaymentType
import com.adematici.turkcellsinav2.util.Constant.DATABASE_NAME
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_TABLE
import com.adematici.turkcellsinav2.util.Constant.PERIOD
import com.adematici.turkcellsinav2.util.Constant.PERIOD_DAY
import com.adematici.turkcellsinav2.util.Constant.TITLE

class PaymentTypeDAO(context: Context) {

    private var paymentTypeDatabase: SQLiteDatabase? = null
    private var dbOpenHelper: DatabaseOpenHelper =
        DatabaseOpenHelper(context, DATABASE_NAME, null, 1)

    private fun open() {
        paymentTypeDatabase = dbOpenHelper.writableDatabase
    }

    private fun close() {
        if (paymentTypeDatabase != null && paymentTypeDatabase!!.isOpen) {
            paymentTypeDatabase!!.close()
        }
    }

    private fun getAllPaymentTypeQuery(): Cursor {
        val query = "SELECT * FROM $PAYMENT_TYPE_TABLE"
        return paymentTypeDatabase!!.rawQuery(query, null)
    }

    @SuppressLint("Range")
    fun getAllPaymentType(): ArrayList<PaymentType> {
        val pList = ArrayList<PaymentType>()
        open()

        val c: Cursor = getAllPaymentTypeQuery()

        if (c.moveToNext()) {
            do {
                val paymentType = PaymentType(
                    id = c.getInt(0),
                    title = c.getString(c.getColumnIndex(TITLE)),
                    period = c.getStringOrNull(c.getColumnIndex(PERIOD)),
                    periodDay = c.getIntOrNull(c.getColumnIndex(PERIOD_DAY))
                )
                pList.add(paymentType)
            } while (c.moveToNext())
        }
        close()
        return pList
    }

    fun addPaymentType(paymentType: PaymentType) {
        val cv = ContentValues()
        cv.put(TITLE, paymentType.title)
        cv.put(PERIOD, paymentType.period)
        cv.put(PERIOD_DAY, paymentType.periodDay)

        open()
        paymentTypeDatabase!!.insert(PAYMENT_TYPE_TABLE, null, cv)
        close()
    }

    fun updatePaymentType(paymentType: PaymentType) {
        val cv = ContentValues()
        cv.put(TITLE, paymentType.title)
        cv.put(PERIOD, paymentType.period)
        cv.put(PERIOD_DAY, paymentType.periodDay)

        open()
        paymentTypeDatabase!!.update(
            PAYMENT_TYPE_TABLE, cv, "id = ?", arrayOf(paymentType.id.toString())
        )
        //close()
    }

    fun deletePaymentType(id: Int) {
        open()
        paymentTypeDatabase!!.delete(PAYMENT_TYPE_TABLE, "id = ?", arrayOf(id.toString()))
        close()
    }

    private fun getSelectedPaymentTypeQuery(id: Int): Cursor {
        val query = "SELECT * FROM $PAYMENT_TYPE_TABLE WHERE id = ?"
        return paymentTypeDatabase!!.rawQuery(query, arrayOf(id.toString()))
    }

    @SuppressLint("Range")
    fun getSelectedPaymentType(id: Int): PaymentType? {
        var paymentType: PaymentType? = null
        open()
        val c: Cursor = getSelectedPaymentTypeQuery(id)
        while (c.moveToNext()) {
            paymentType = PaymentType(
                id = c.getInt(0),
                title = c.getString(c.getColumnIndex(TITLE)),
                period = c.getString(c.getColumnIndex(PERIOD)),
                periodDay = c.getIntOrNull(c.getColumnIndex(PERIOD_DAY))
            )
        }
        close()
        return paymentType
    }

}