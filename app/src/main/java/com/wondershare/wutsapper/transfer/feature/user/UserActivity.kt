package com.wondershare.wutsapper.transfer.feature.user

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityUserBinding
import com.wondershare.wutsapper.transfer.databinding.DialogBuyPremiumBinding
import com.wondershare.wutsapper.transfer.databinding.DialogRequestBuyPremiumBinding
import com.wondershare.wutsapper.transfer.feature.user.faqs.FAQsActivity
import kotlinx.android.synthetic.main.activity_user.*


class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, UserActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        binding.executePendingBindings()
        initView()
        actionView()
    }

    private fun actionView() {
        binding.FAQs.setOnClickListener {
            FAQsActivity.startActivity(this)
        }

        binding.btnBuyPremium.setOnClickListener {
            dialogBuyPremium().show()
        }
    }

    private fun initView() {
        binding.toolbar.btnToolbar.visibility = View.GONE

        binding.txtNameDevice.text = getDeviceName()
    }

    private fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model
        } else "$manufacturer $model"
    }

    fun dialogBuyPremium(): Dialog {
        val dialog = Dialog(this)
        val dialogBinding: DialogRequestBuyPremiumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(dialog.context),
            R.layout.dialog_request_buy_premium,
            dialog.findViewById(R.id.view),
            false
        )
        dialog.setContentView(dialogBinding.root)
        dialog.window!!.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setGravity(Gravity.CENTER)
        val dp17 = resources.getDimension(com.intuit.sdp.R.dimen._17sdp).toInt()
        dialog.window!!.setBackgroundDrawable(
            InsetDrawable(
                ColorDrawable(Color.TRANSPARENT),
                dp17,
                0,
                dp17,
                0
            )
        )


        return dialog
    }
}