package com.basic.common.extension

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Environment
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearSmoothScroller
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun Context.getColorCompat(colorRes: Int): Int {
    return tryOrNull { ContextCompat.getColor(this, colorRes) } ?: Color.BLACK
}

fun <T> tryOrNull(logOnError: Boolean = true, body: () -> T?): T? {
    return try {
        body()
    } catch (e: Exception) {
        if (logOnError) {
            Timber.e("Error: $e")
        }
        null
    }
}

fun Context.fileFromAsset(fileName: String): File = File(cacheDir, fileName)
    .also {
        if (!it.exists()) {
            it.outputStream().use { cache ->
                assets.open(fileName).use { inputStream ->
                    inputStream.copyTo(cache)
                }
            }
        }
    }

@ColorInt
fun Context.resolveAttrColor(@AttrRes attr: Int): Int {
    val a = theme.obtainStyledAttributes(intArrayOf(attr))
    val color: Int
    try {
        color = a.getColor(0, 0)
    } finally {
        a.recycle()
    }
    return color
}

fun Context.getDimens(@DimenRes dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

fun Context.convertBitmapToFile(bitmapImage: Bitmap): File {
    val cw = ContextWrapper(this)
    // path to /data/data/yourapp/app_data/imageDir
    val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
    // Create imageDir
    val mypath = File(directory, "${System.currentTimeMillis()}_avatar.jpg")
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(mypath)
        // Use the compress method on the BitMap object to write image to the OutputStream
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    } finally {
        try {
            fos!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return mypath
}

fun Context.convertBitmapToFilePublic(bitmapImage: Bitmap): File {
    val cw = ContextWrapper(this)
    // path to /data/data/yourapp/app_data/imageDir
    val directory: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
    // Create imageDir
    val mypath = File(directory, "${System.currentTimeMillis()}_avatar.jpg")
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(mypath)
        // Use the compress method on the BitMap object to write image to the OutputStream
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    } finally {
        try {
            fos!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return mypath
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun Context.getSmoothScroll(): LinearSmoothScroller {
    return object : LinearSmoothScroller(this) {
        override fun getVerticalSnapPreference(): Int {
            return LinearSmoothScroller.SNAP_TO_START
        }
    }
}
