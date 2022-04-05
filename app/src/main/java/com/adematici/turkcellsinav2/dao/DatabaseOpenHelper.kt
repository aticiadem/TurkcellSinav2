package com.adematici.turkcellsinav2.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TABLE
import com.adematici.turkcellsinav2.util.Constant.PAYMENT_TYPE_TABLE

class DatabaseOpenHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        val paymentTypeTable =
            "CREATE TABLE $PAYMENT_TYPE_TABLE(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT NOT NULL, period TEXT, period_day INTEGER)"
        val paymentTable =
            "CREATE TABLE $PAYMENT_TABLE(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, amount DOUBLE, date TEXT, payment_type INTEGER)"

        db.execSQL(paymentTypeTable)
        db.execSQL(paymentTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $PAYMENT_TYPE_TABLE")
        db.execSQL("DROP TABLE IF EXISTS $PAYMENT_TABLE")
        onCreate(db)
    }

}