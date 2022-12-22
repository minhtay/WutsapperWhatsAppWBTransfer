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
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityUserBinding
import com.wondershare.wutsapper.transfer.databinding.DialogBuyPremiumBinding
import com.wondershare.wutsapper.transfer.databinding.DialogRequestBuyPremiumBinding
import com.wondershare.wutsapper.transfer.feature.user.faqs.FAQsActivity
import com.wondershare.wutsapper.transfer.feature.user.toolkit.ToolkitActivity


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

        binding.toolkit.setOnClickListener {
            ToolkitActivity.startActivity(this)
        }

        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initView() {
        binding.toolbar.btnToolbar.visibility = View.GONE

        binding.txtNameDevice.text = getDeviceName()

        dialogRequestBuyPremium().show()
    }

    private fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model
        } else "$manufacturer $model"
    }

    private fun dialogRequestBuyPremium(): Dialog {
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

    private fun dialogBuyPremium(): Dialog {
        var check: Int = 2
        val dialog = Dialog(this)
        val dialogBinding: DialogBuyPremiumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(dialog.context),
            R.layout.dialog_buy_premium,
            dialog.findViewById(R.id.view),
            false
        )
        dialog.setContentView(dialogBinding.root)
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawableResource(R.color.trans)
        dialog.window!!.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )

        dialog.setCanceledOnTouchOutside(false)
        val dp3 = resources.getDimension(com.intuit.sdp.R.dimen._3sdp).toInt()

        // defaut prWa is select
        dialogBinding.prWa.appCompatCheckBox.isChecked = true
        dialogBinding.prWa.cardView.strokeColor =
            ContextCompat.getColor(this, R.color.color_0038FF)
        dialogBinding.prWa.cardView.strokeWidth = dp3
        dialogBinding.prWa.cardView.cardElevation = 10f
        check = 1

        dialogBinding.prWa.cardView.setOnClickListener {
            if (check != 1) {

                clearSelectBuyPremium(dialogBinding)
                dialogBinding.prWa.appCompatCheckBox.isChecked = true
                dialogBinding.prWa.cardView.strokeColor =
                    ContextCompat.getColor(this, R.color.color_0038FF)
                dialogBinding.prWa.cardView.strokeWidth = dp3
                dialogBinding.prWa.cardView.cardElevation = 10f
                check = 1
            }
        }

        dialogBinding.prWab.cardView.setOnClickListener {
            if (check != 2) {

                clearSelectBuyPremium(dialogBinding)
                dialogBinding.prWab.appCompatCheckBox.isChecked = true
                dialogBinding.prWab.cardView.strokeColor =
                    ContextCompat.getColor(this, R.color.color_0038FF)
                dialogBinding.prWab.cardView.strokeWidth = dp3
                dialogBinding.prWab.cardView.cardElevation = 10f

                check = 2
            }
        }

        dialogBinding.prWaWab.cardView.setOnClickListener {
            if (check != 3) {

                clearSelectBuyPremium(dialogBinding)
                dialogBinding.prWaWab.appCompatCheckBox.isChecked = true
                dialogBinding.prWaWab.cardView.strokeColor =
                    ContextCompat.getColor(this, R.color.color_0038FF)
                dialogBinding.prWaWab.cardView.strokeWidth = dp3
                dialogBinding.prWaWab.cardView.cardElevation = 10f

                check = 3
            }
        }

        return dialog
    }

    private fun clearSelectBuyPremium(dialogBinding: DialogBuyPremiumBinding) {
        dialogBinding.prWa.appCompatCheckBox.isChecked = false
        dialogBinding.prWa.cardView.strokeColor =
            ContextCompat.getColor(this, R.color.trans)
        dialogBinding.prWa.cardView.strokeWidth = 0
        dialogBinding.prWa.cardView.cardElevation = 0f

        dialogBinding.prWaWab.appCompatCheckBox.isChecked = false
        dialogBinding.prWaWab.cardView.strokeColor =
            ContextCompat.getColor(this, R.color.trans)
        dialogBinding.prWaWab.cardView.strokeWidth = 0
        dialogBinding.prWaWab.cardView.cardElevation = 0f

        dialogBinding.prWab.appCompatCheckBox.isChecked = false
        dialogBinding.prWab.cardView.strokeColor =
            ContextCompat.getColor(this, R.color.trans)
        dialogBinding.prWab.cardView.strokeWidth = 0
        dialogBinding.prWab.cardView.cardElevation = 0f
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}