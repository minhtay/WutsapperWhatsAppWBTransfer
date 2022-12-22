package com.wondershare.wutsapper.transfer.feature.backup.receive_file

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityReceiveFileBlutoothBinding
import com.wondershare.wutsapper.transfer.databinding.ActivityShareFileBinding

class ReceiveFileBlutoothActivity : AppCompatActivity() {

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, ReceiveFileBlutoothActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityReceiveFileBlutoothBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_receive_file_blutooth)
        binding.executePendingBindings()

        binding.btnBack.setOnClickListener { onBackPressed() }

    }
}