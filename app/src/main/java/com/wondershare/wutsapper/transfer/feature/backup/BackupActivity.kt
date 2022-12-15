package com.wondershare.wutsapper.transfer.feature.backup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityBackupBinding
import com.wondershare.wutsapper.transfer.feature.backup.model.CoutryPhoneDataRCV
import com.wondershare.wutsapper.transfer.feature.backup.step.Step1Fragment
import com.wondershare.wutsapper.transfer.feature.backup.step.Step2Fragment
import com.wondershare.wutsapper.transfer.feature.base.BaseActivity
import com.wondershare.wutsapper.transfer.feature.user.UserActivity
import kotlinx.android.synthetic.main.activity_coutry_phone.*

class BackupActivity : BaseActivity<ActivityBackupBinding, BackupViewmodel>(), BackupNavigator {

    val TAG = "BackupActivityResuft"

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, BackupActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityBackupBinding
    private lateinit var viewmodel: BackupViewmodel

    val coutryPhoneActivity =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bundel = result.data
                if (bundel!!.extras != null) {
                    val data = bundel.extras!!.getSerializable("results") as CoutryPhoneDataRCV
                    viewmodel.coutryPhone.postValue(data.country)
                    viewmodel._codePhone.postValue(data.code)
                    Log.d(TAG, ": ")
                }
            }

        }

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.activity_backup
    override val mViewmodel: BackupViewmodel
        get() = ViewModelProvider(this)[BackupViewmodel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = viewDataBinding!!
        this.binding.lifecycleOwner = this
        viewmodel = mViewmodel
        initView()
        actionView()

        //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    private fun actionView() {
        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initView() {
        addFragment(Step1Fragment(), binding.fragmentView.id, "Step1")

        binding.root.setOnClickListener { hideKeyboard() }

        viewmodel.statebar.observe(this) {
            refeshStatebar()
            when (it) {
                1 -> {
                    binding.stateProgressbar.img1.setImageResource(R.drawable.img_stateprogessbar)
                    binding.stateProgressbar.txt1.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_2156DF
                        )
                    )
                }
                2 -> {
                    binding.stateProgressbar.img2.setImageResource(R.drawable.img_stateprogessbar)
                    binding.stateProgressbar.txt2.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_2156DF
                        )
                    )
                }
                3 -> {
                    binding.stateProgressbar.img3.setImageResource(R.drawable.img_stateprogessbar)
                    binding.stateProgressbar.txt3.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_2156DF
                        )
                    )
                }
                4 -> {
                    binding.stateProgressbar.img4.setImageResource(R.drawable.img_stateprogessbar)
                    binding.stateProgressbar.txt4.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.color_2156DF
                        )
                    )
                }
            }
        }

        binding.toolbar.btnToolbar.setOnClickListener {
            UserActivity.startActivity(this)
        }
    }

    private fun refeshStatebar() {
        binding.stateProgressbar.img1.setImageResource(0)
        binding.stateProgressbar.img2.setImageResource(0)
        binding.stateProgressbar.img3.setImageResource(0)
        binding.stateProgressbar.img4.setImageResource(0)
        binding.stateProgressbar.txt1.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.color_9199B0
            )
        )
        binding.stateProgressbar.txt2.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.color_9199B0
            )
        )
        binding.stateProgressbar.txt3.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.color_9199B0
            )
        )
        binding.stateProgressbar.txt3.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.color_9199B0
            )
        )

    }

    override fun navigateToScreen(Tag: Int) {
        addFragment(Step2Fragment(), binding.fragmentView.id, "Step2")
    }

    override fun onBackPressed() {
        when (viewmodel.backstack.value) {
            1 -> finish()
            2 -> {
                onBackstackFragment()
                viewmodel.statebar.postValue(1)
            }
            3 -> {
                onBackstackFragment()
                viewmodel.statebar.postValue(2)
            }

        }
    }


}