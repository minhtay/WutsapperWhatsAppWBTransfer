package com.basic.common.extension

import android.content.Context
import android.graphics.Color
import android.util.TypedValue

fun Int.dpToPx(context: Context?): Float {
    return context?.let { TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics) } ?: run { 0f }
}

fun Float.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)
}

fun Int.withAlpha(alpha: Int): Int {
    return Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}

fun Int.forEach(action: (Int) -> Unit) {
    for (index in 0 until this) {
        action(index)
    }
}

fun Float.within(min: Float, max: Float): Float {
    return Math.min(max, Math.max(min, this))
}

fun Long.toTimeFormat(): String {
    val hours = this / 3600
    val secondsLeft = this - hours * 3600
    val minutes = secondsLeft / 60
    val seconds = secondsLeft - minutes * 60

    var formattedTime = ""
    if (hours < 10) formattedTime += "0"
    formattedTime += "$hours:"

    if (minutes < 10) formattedTime += "0"
    formattedTime += "$minutes:"

    if (seconds < 10) formattedTime += "0"
    formattedTime += seconds

    return formattedTime
}