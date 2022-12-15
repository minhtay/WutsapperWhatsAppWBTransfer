package com.wondershare.wutsapper.transfer.feature.base

import android.app.Dialog
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.wondershare.wutsapper.transfer.feature.backup.step.Step1Fragment

interface BaseNavigator {
    fun showToast(msg: String)

    fun showSnackbar()

    fun showSnackbarWithRetry()

    fun showDialog(dialog: Dialog)

    fun showOffline()

    fun hideSnackbar()

    fun hideKeyboard()

    fun hideDialog(dialog: Dialog)

    fun gone(view: View)

    fun visible(view: View)
}