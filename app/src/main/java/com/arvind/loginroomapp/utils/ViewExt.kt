package com.arvind.loginroomapp.utils

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

// parse string to double
fun parseDouble(value: String?): Double {
    return if (value == null || value.isEmpty()) Double.NaN else value.toDouble()
}

// indian rupee converter
fun indianRupee(amount: Double): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("INR")
    return format.format(amount)
}


val String.cleanTextContent: String
    get() {
        // strips off all non-ASCII characters
        var text = this
        text = text.replace("[^\\x00-\\x7F]".toRegex(), "")

        // erases all the ASCII control characters
        text = text.replace("[\\p{Cntrl}&&[^\r\n\t]]".toRegex(), "")

        // removes non-printable characters from Unicode
        text = text.replace("\\p{C}".toRegex(), "")
        text = text.replace(",".toRegex(), "")
        return text.trim()
    }
