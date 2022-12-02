package com.basic.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.basic.data.Preferences
import com.widget.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

abstract class LsThemedActivity: AppCompatActivity() {

    @Inject lateinit var prefs: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        when(prefs.mode.get()){
//            Preferences.AUTO_MODE, Preferences.NIGHT_MODE -> setTheme(R.style.Theme_LsPhotoEditor_Dark)
//            else -> setTheme(R.style.Theme_LsPhotoEditor)
//        }
//        Timber.e("Mode: ${prefs.mode.get()}")
    }

}