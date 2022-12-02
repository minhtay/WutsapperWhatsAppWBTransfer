package com.basic.common.extension

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


fun Long.isYesterday(): Boolean {
    return DateUtils.isToday(this + DateUtils.DAY_IN_MILLIS)
}

fun Long.isToday(): Boolean {
    return DateUtils.isToday(this)
}

@SuppressLint("SimpleDateFormat")
fun Long.formatToDDMMYYYY(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(this))
}

@SuppressLint("SimpleDateFormat")
fun Long.formatToMMSS(): String {
    val formatter = SimpleDateFormat("mm:ss")
    return formatter.format(Date(this))
}

@SuppressLint("SimpleDateFormat")
fun Long.formatToHHMM(): String {
    val formatter = SimpleDateFormat("HH:mm")
    return formatter.format(Date(this))
}