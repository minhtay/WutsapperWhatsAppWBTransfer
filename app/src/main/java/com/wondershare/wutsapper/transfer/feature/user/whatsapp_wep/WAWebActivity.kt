package com.wondershare.wutsapper.transfer.feature.user.whatsapp_wep

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityDeletedBinding
import com.wondershare.wutsapper.transfer.databinding.ActivityWawebBinding

class WAWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWawebBinding

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context,WAWebActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_waweb)
        binding.executePendingBindings()

    }
}