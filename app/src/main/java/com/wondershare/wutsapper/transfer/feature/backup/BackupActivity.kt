package com.wondershare.wutsapper.transfer.feature.backup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.ActivityBackupBinding
import com.wondershare.wutsapper.transfer.feature.backup.adapter.BackupAdapterViewPager
import com.wondershare.wutsapper.transfer.feature.backup.adapter.BackupAdapterViewpagerOption2
import com.wondershare.wutsapper.transfer.feature.backup.model.CoutryPhoneDataRCV
import com.wondershare.wutsapper.transfer.feature.backup.step.DeviceConnectionFragment
import com.wondershare.wutsapper.transfer.feature.backup.step.Step1Fragment
import com.wondershare.wutsapper.transfer.feature.backup.step.Step2Fragment
import com.wondershare.wutsapper.transfer.feature.base.BaseActivity
import com.wondershare.wutsapper.transfer.feature.user.UserActivity

class BackupActivity : BaseActivity<ActivityBackupBinding, BackupViewmodel>() {

    companion object {
        fun startActivity(context: Context, currentIntent: Intent, option: Int) {
            val intent = Intent(context, BackupActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("option", option)
            intent.putExtras(bundle)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    lateinit var binding: ActivityBackupBinding
    private lateinit var viewmodel: BackupViewmodel

    val coutryPhoneActivity =
        registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bundel = result.data
                if (bundel!!.extras != null) {
                    val data = bundel.extras!!.getSerializable("results") as CoutryPhoneDataRCV
                    viewmodel.coutryPhone.postValue(data.country)
                    viewmodel._codePhone.postValue(data.code)
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

    }

    private fun actionView() {
        binding.toolbar.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initView() {

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)



        when (intent.getSerializableExtra("option")) {
            1 -> {
                val pagerAdapter = BackupAdapterViewPager(this)
                binding.fragmentView.adapter = pagerAdapter
                binding.fragmentView.isUserInputEnabled = false
            }

            2 -> {
                val pagerAdapter = BackupAdapterViewpagerOption2(this)
                binding.fragmentView.adapter = pagerAdapter
                binding.fragmentView.isUserInputEnabled = false
            }
        }

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

    override fun onBackPressed() {
        when (viewmodel.backstack.value) {
            1 -> finish()
            2 -> {
                binding.fragmentView.currentItem = 0
                viewmodel.statebar.postValue(1)
                viewmodel.nameToolbar.postValue(resources.getString(R.string.phone_number))
            }


        }
    }


}