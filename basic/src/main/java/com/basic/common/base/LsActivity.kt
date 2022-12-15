package com.basic.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.basic.common.extension.transparent

abstract class LsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        transparent()
//        when {
//            prefs.isNightMode.get() -> {
//                setTheme(R.style.Theme_LsVpn_Dark)
//
//                StatusBarCompat.cancelLightStatusBar(this)
//                StatusBarCompat.cancelLightNavigationBar(this)
//            }
//            else -> {
//                setTheme(R.style.Theme_LsVpn)
//
//                StatusBarCompat.changeToLightStatusBar(this)
//                StatusBarCompat.changeToLightNavigationBar(this)
//            }
//        }
        super.onCreate(savedInstanceState)

//        val triggers = listOf(prefs.isNightMode)
//        Observable.merge(triggers.map { it.asObservable().skip(1) })
//            .debounce(400, TimeUnit.MILLISECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .autoDispose(scope())
//            .subscribe {
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                startActivity(intent)
//            }
    }

//    open fun getActivityThemeRes(isNightMode: Boolean) = when {
//        isNightMode -> R.style.Theme_LsVpn_Dark
//        else -> R.style.Theme_LsVpn
//    }

}