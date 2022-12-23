package com.wondershare.wutsapper.transfer.feature.backup.waiting_share

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.autoDispose
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityWaitingSenderBinding
import com.wondershare.wutsapper.transfer.feature.backup.select_file.SelectFileActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class WaitingSenderActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, WaitingSenderActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityWaitingSenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_waiting_sender)
        binding.executePendingBindings()


        Observable.timer(5, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .autoDispose(scope())
            .subscribe {
                SelectFileActivity.startActivity(this@WaitingSenderActivity)
                finish()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}