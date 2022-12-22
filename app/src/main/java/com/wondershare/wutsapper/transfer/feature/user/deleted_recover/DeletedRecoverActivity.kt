package com.wondershare.wutsapper.transfer.feature.user.deleted_recover

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityDeletedBinding

class DeletedRecoverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeletedBinding

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, DeletedRecoverActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_deleted)
        binding.executePendingBindings()

        binding.toolbar.txtToolbar.text = resources.getString(R.string.whatsapp_deleted)
        binding.toolbar.btnBack.setOnClickListener { onBackPressed() }
        binding.toolbar.btnToolbar.visibility = View.GONE
        binding.toolbar.menuToolbar.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}