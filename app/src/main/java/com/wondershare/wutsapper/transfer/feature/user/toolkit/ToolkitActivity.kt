package com.wondershare.wutsapper.transfer.feature.user.toolkit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityToolkitBinding
import com.wondershare.wutsapper.transfer.feature.user.deleted.DeletedActivity
import com.wondershare.wutsapper.transfer.feature.user.status_saver.StatusSaverActivity
import com.wondershare.wutsapper.transfer.feature.user.whatsapp_wep.WAWebActivity

class ToolkitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToolkitBinding

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context,ToolkitActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_toolkit)
        binding.executePendingBindings()

        binding.toolbar.txtToolbar.text = resources.getString(R.string.toolkit)
        binding.toolbar.btnToolbar.visibility = View.GONE
        binding.toolbar.menuToolbar.visibility = View.VISIBLE

        binding.tk1.view1.setOnClickListener {
            StatusSaverActivity.startActivity(this)
        }

        binding.tk2.view2.setOnClickListener {
            DeletedActivity.startActivity(this)
        }

        binding.tk3.view3.setOnClickListener {
            WAWebActivity.startActivity(this)
        }

        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}