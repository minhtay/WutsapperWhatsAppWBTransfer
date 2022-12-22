package com.wondershare.wutsapper.transfer.feature.backup.step


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
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

    override fun onResume() {
        super.onResume()
        initView()
        actionView()
    }

    private fun initView() {
        viewmodel.backstack.postValue(1)
        viewmodel.nameToolbar.postValue(resources.getString(R.string.phone_number))
        viewmodel.statebar.postValue(1)

        viewmodel.coutryPhone.observe(activity) {
            if (it != ""){
                binding.txtcoutry.text = it
            }
        }
        viewmodel._codePhone.observe(activity){
            if (it!=""){
                binding.code.setText(it)
            }
        }
    }


    private fun actionView() {
        binding.changeCoutryPhone.setOnClickListener {
            binding.code.removeTextChangedListener(textChanges)

            val intent = Intent(activity, CoutryPhoneActivity::class.java)
            activity.coutryPhoneActivity.launch(intent)
        }

        binding.code.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.code.addTextChangedListener(textChanges)
            } else {
                binding.code.removeTextChangedListener(textChanges)
            }
        }

        binding.btnNext.setOnClickListener {
            if (invalid()){
                activity.binding.fragmentView.currentItem = 1
            }else Toast.makeText(activity,"Please enter a phone number or select a country  ",Toast.LENGTH_LONG).show()
        }
    }

    private val textChanges by lazy {
        object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    val data = Utils.loadJSONFromAsset(activity)!!.singleOrNull {
                        it.code == s.toString()
                    }
                    if (data != null) {
                        viewmodel.coutryPhone.postValue(data.country)
                        viewmodel._codePhone.postValue(data.code)
                        binding.code.clearFocus()
                        binding.phoneNumber.requestFocus()
                    }else{
                        viewmodel.coutryPhone.postValue(resources.getString(R.string.invalid_country_code))
                        viewmodel._codePhone.postValue("")
                    }
                } else {
                    viewmodel.coutryPhone.postValue(resources.getString(R.string.choose_a_country))
                    viewmodel._codePhone.postValue("")
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
    }

    private fun invalid(): Boolean {
        return !(viewmodel._codePhone.value == "" || viewmodel._phoneNumber.value == null)
    }
}