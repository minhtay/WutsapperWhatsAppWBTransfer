package com.wondershare.wutsapper.transfer.feature.user.faqs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityFaqsBinding
import com.wondershare.wutsapper.transfer.databinding.DialogLowBatteryBinding
import com.wondershare.wutsapper.transfer.feature.backup.BackupActivity
import com.wondershare.wutsapper.transfer.feature.guide_backup.GuideBackupActivity

class FAQsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaqsBinding

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, FAQsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_faqs)
        binding.executePendingBindings()

        binding.toolbar.btnToolbar.visibility = View.GONE
        binding.toolbar.txtToolbar.text = "FAQs"
        binding.toolbar.btnBack.setOnClickListener { onBackPressed() }
        binding.webview.loadUrl("file:///android_asset/FAQs.html")
    }


}