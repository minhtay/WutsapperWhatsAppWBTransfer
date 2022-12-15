package com.wondershare.wutsapper.transfer.feature.user.deleted

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityDeletedBinding

class DeletedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeletedBinding

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, DeletedActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_deleted)
        binding.executePendingBindings()


    }
}