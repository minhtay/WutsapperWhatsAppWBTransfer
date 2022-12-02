package com.wondershare.wutsapper.transfer.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.domain.manager.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when {
            permissionManager.hasCall() -> {}
        }
    }

}