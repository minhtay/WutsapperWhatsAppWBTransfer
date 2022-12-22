package com.wondershare.wutsapper.transfer.feature.backup.select_file

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivitySelectFileBinding
import com.wondershare.wutsapper.transfer.feature.backup.adapter.SelectFileAdapterRCV

class SelectFileActivity : AppCompatActivity() {

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context,SelectFileActivity::class.java)
            context.startActivity(intent)
        }
    }
    private lateinit var binding: ActivitySelectFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_file)
        binding.executePendingBindings()

        val arrayList :ArrayList<String> = arrayListOf("1","2","3","4","5","6","7","8")

        binding.rcvSelectFile.apply {
            layoutManager = LinearLayoutManager(this@SelectFileActivity,LinearLayoutManager.VERTICAL,false)
            adapter = SelectFileAdapterRCV(arrayList)
        }
    }
}