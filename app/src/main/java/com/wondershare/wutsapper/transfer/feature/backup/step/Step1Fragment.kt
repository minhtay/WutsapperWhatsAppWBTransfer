package com.wondershare.wutsapper.transfer.feature.backup.step


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.FragmentStep1Binding
import com.wondershare.wutsapper.transfer.feature.backup.BackupActivity
import com.wondershare.wutsapper.transfer.feature.backup.BackupViewmodel
import com.wondershare.wutsapper.transfer.feature.backup.CoutryPhoneActivity
import com.wondershare.wutsapper.transfer.feature.base.BaseFragment
import com.wondershare.wutsapper.transfer.utils.Utils

class Step1Fragment : BaseFragment<FragmentStep1Binding, BackupViewmodel>() {

    private val activity by lazy { requireActivity() as BackupActivity }
    private lateinit var binding: FragmentStep1Binding
    private lateinit var viewmodel: BackupViewmodel

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.fragment_step1
    override val mViewmodel: BackupViewmodel
        get() = activity.mViewmodel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!
        viewmodel = mViewmodel
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        initView()
        actionView()
    }

    private fun initView() {
        viewmodel.backstack.postValue(1)
        viewmodel.nameToolbar.postValue(resources.getString(R.string.phone_number))
        viewmodel.statebar.postValue(1)

        viewmodel.coutryPhone.observe(activity) {
            if (it.isNotEmpty()){
                binding.txtcoutry.text = it
            }
        }
        viewmodel._codePhone.observe(activity) {
            Log.d("TAG", "initView: ")
        }
    }


    @SuppressLint("NewApi")
    private fun actionView() {
        binding.btnNext.setOnClickListener {
            (baseActivity as BackupActivity).navigateToScreen(1)
        }

        binding.changeCoutryPhone.setOnClickListener {
            val intent = Intent(activity, CoutryPhoneActivity::class.java)
            activity.coutryPhoneActivity.launch(intent)
        }

        binding.code.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.code.addTextChangedListener { textChange ->
                    if (textChange!!.isNotEmpty()) {
                        val data = Utils.loadJSONFromAsset(activity)!!.singleOrNull {
                            it.code == textChange.toString()
                        }
                        if (data != null) {
                            viewmodel.coutryPhone.postValue(data.country)
                            binding.code.clearFocus()
                            binding.phoneNumber.requestFocus()
                            binding.phoneNumber.isCursorVisible
                        }else{
                            binding.txtcoutry.text = resources.getText(R.string.invalid_country_code)
                        }
                    } else {
                        binding.txtcoutry.text = resources.getText(R.string.choose_a_country)

                    }

                }
            }
        }
    }
}