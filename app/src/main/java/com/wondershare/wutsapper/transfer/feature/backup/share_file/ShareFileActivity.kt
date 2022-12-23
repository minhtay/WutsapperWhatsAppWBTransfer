package com.wondershare.wutsapper.transfer.feature.backup.share_file

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wondershare.wutsapper.transfer.R
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.databinding.ActivityShareFileBinding
import com.wondershare.wutsapper.transfer.feature.backup.receive_file.ReceiveFileBlutoothActivity
import com.wondershare.wutsapper.transfer.feature.backup.waiting_share.WaitingSenderActivity


class ShareFileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShareFileBinding

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ShareFileActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_file)
        binding.executePendingBindings()

        binding.toolbar.btnBack.setOnClickListener { onBackPressed() }

        binding.toolbar.txtToolbar.text = resources.getString(R.string.share_file)
        binding.toolbar.btnToolbar.visibility = View.GONE


        binding.network.setOnClickListener {
            WaitingSenderActivity.startActivity(this)

        }

        binding.blutooth.setOnClickListener {
            ReceiveFileBlutoothActivity.startActivity(this)
        }


    }
}