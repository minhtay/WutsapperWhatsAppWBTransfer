package com.wondershare.wutsapper.transfer.feature.home

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.basic.data.Preferences
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityHomeBinding
import com.wondershare.wutsapper.transfer.databinding.DialogLowBatteryBinding
import com.wondershare.wutsapper.transfer.databinding.DialogRequireBackupBinding
import com.wondershare.wutsapper.transfer.feature.backup.BackupActivity
import com.wondershare.wutsapper.transfer.feature.base.BaseActivity
import com.wondershare.wutsapper.transfer.feature.guide_backup.GuideBackupActivity
import com.wondershare.wutsapper.transfer.feature.user.UserActivity
import com.wondershare.wutsapper.transfer.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewmodel>() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewmodel: HomeViewmodel

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.activity_home
    override val mViewmodel: HomeViewmodel
        get() = ViewModelProvider(this)[HomeViewmodel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!
        viewmodel = mViewmodel
        initView()
        actionView()
    }

    private fun initView() {

        binding.actionBar.txtToolbar.setText(R.string.text_toolbar_home)
        gone(binding.actionBar.btnBack)

    }

    private fun actionView() {
        viewmodel.showMenu.observe(this) {
            if (it) {
                visible(binding.dialogTranferApp)
                binding.imgDropOrDown.setImageResource(R.drawable.ic_dropup_menu_home)
            } else {
                gone(binding.dialogTranferApp)
                binding.imgDropOrDown.setImageResource(R.drawable.ic_dropdown_menu_home)

            }
        }

        binding.btnChooseApp.setOnClickListener {
            if (!viewmodel.showMenu.value!!) {
                viewmodel.showMenu.postValue(true)
            } else viewmodel.showMenu.postValue(false)
        }

        binding.tranfer.view.setOnClickListener {
            showDialog(dialogRequireBackup())
        }

        binding.root.setOnClickListener {
            if (binding.dialogTranferApp.visibility == View.VISIBLE) {
                binding.dialogTranferApp.visibility = View.GONE
            }
        }

        binding.actionBar.btnToolbar.setOnClickListener {
            UserActivity.startActivity(this)
        }


    }

    private fun dialogRequireBackup(): Dialog {
        val dialog = Dialog(this)

        val dialogBinding: DialogRequireBackupBinding = DataBindingUtil.inflate(
            LayoutInflater.from(dialog.context),
            R.layout.dialog_require_backup,
            dialog.findViewById(R.id.view),
            false
        )
        dialog.setContentView(dialogBinding.root)
        dialogBinding.viewmodel = viewmodel
        dialog.window!!.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
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
        dialogBinding.btnRebackup.setOnClickListener {
            if (Utils.getBatteryLevel(this@HomeActivity, intent) < 50) {
                viewmodel.paramester.postValue(1)
                showDialog(dialogLowBattery())
                hideDialog(dialog)
            } else {
                GuideBackupActivity.startActivity(this)
                hideDialog(dialog)
            }
        }

        dialogBinding.btnBackup.setOnClickListener {
            if (Utils.getBatteryLevel(this@HomeActivity, intent) < 50) {
                viewmodel.paramester.postValue(2)
                showDialog(dialogLowBattery())
                hideDialog(dialog)
            } else {
                BackupActivity.startActivity(this)
                hideDialog(dialog)
            }
        }
        return dialog
    }

    private fun dialogLowBattery(): Dialog {
        val dialog = Dialog(this)
        val dialogBinding: DialogLowBatteryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(dialog.context),
            R.layout.dialog_low_battery,
            dialog.findViewById(R.id.view),
            false
        )
        dialog.setContentView(dialogBinding.root)
        dialogBinding.viewmodel = viewmodel
        dialog.window!!.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawableResource(R.color.trans)

        dialogBinding.btnOk.setOnClickListener {
            when (viewmodel.paramester.value) {
                1 -> GuideBackupActivity.startActivity(this)
                2 -> BackupActivity.startActivity(this)
            }
            hideDialog(dialog)
        }
        return dialog
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}