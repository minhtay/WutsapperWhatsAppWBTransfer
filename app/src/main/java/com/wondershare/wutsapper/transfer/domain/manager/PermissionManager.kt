package com.wondershare.wutsapper.transfer.domain.manager

import android.app.Activity
import androidx.fragment.app.Fragment

interface PermissionManager {

    fun hasPhoneReceiver(): Boolean

    fun requestPhoneReceiver(activity: Activity, resultCode: Int)

    fun hasOverlay(): Boolean

    fun hasCall(): Boolean

    fun requestCall(activity: Activity, resultCode: Int)

    fun hasStorage(): Boolean

    fun requestStorage(activity: Activity, resultCode: Int)

    fun hasContact(): Boolean

    fun requestContact(activity: Activity, resultCode: Int)

    fun requestContact(fragment: Fragment, resultCode: Int)

}