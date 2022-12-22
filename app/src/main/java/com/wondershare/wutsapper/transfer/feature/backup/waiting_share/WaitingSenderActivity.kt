package com.wondershare.wutsapper.transfer.feature.backup.waiting_share

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityWaitingSenderBinding
import com.wondershare.wutsapper.transfer.feature.backup.select_file.SelectFileActivity

class WaitingSenderActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, WaitingSenderActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityWaitingSenderBinding

    private val thread: Thread = object : Thread() {
        override fun run() {
            try {
                sleep(50000)
                SelectFileActivity.startActivity(this@WaitingSenderActivity)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_waiting_sender)
        binding.executePendingBindings()
        thread.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        thread.isInterrupted
    }
}