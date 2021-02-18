package com.thekeval.covid19tracker.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("setUpdatedOn")
fun setUpdatedOn(tv: TextView, updatedOn: String) {
    if (updatedOn.isNullOrEmpty()) return
    // tv.text = updatedOn.toDate().formatTo("dd-MM-yyyy hh:mm:ss")
    tv.text = updatedOn
}

fun String.toDate(dateFormat: String = "yyyy-MM-dd HH:mm:ss", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}