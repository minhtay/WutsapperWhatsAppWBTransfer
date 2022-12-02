package com.wondershare.wutsapper.transfer.data.manager

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.wondershare.wutsapper.transfer.domain.manager.PermissionManager
import javax.inject.Inject

class PermissionManagerImpl @Inject constructor(
    private val context: Context
) : PermissionManager {

    private fun hasPermissions(vararg permissions: String): Boolean {
        var hasPermission = true
        permissions.forEach {
            val granted =
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            if (!granted) {
                hasPermission = false
                return@forEach
            }
        }
        return hasPermission
    }

    override fun hasPhoneReceiver(): Boolean {
        return hasPermissions(Manifest.permission.READ_PHONE_STATE)
    }

    override fun requestPhoneReceiver(activity: Activity, resultCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_PHONE_STATE), resultCode)
    }

    override fun hasOverlay(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> Settings.canDrawOverlays(context)
            else -> true
        }
    }

    override fun hasCall(): Boolean {
        return hasPermissions(
            Manifest.permission.CALL_PHONE
        )
    }

    override fun requestCall(activity: Activity, resultCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(
            Manifest.permission.CALL_PHONE
        ), resultCode)
    }

    override fun hasStorage(): Boolean {
//        return hasPermissions(
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        )
        return true
    }

    override fun requestStorage(activity: Activity, resultCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), resultCode)
    }

    override fun hasContact(): Boolean {
        return hasPermissions(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
        )
    }

    override fun requestContact(activity: Activity, resultCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
        ), resultCode)
    }

    override fun requestContact(fragment: Fragment, resultCode: Int) {
        fragment.requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS), resultCode)
    }

}