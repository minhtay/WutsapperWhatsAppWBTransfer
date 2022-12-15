package com.wondershare.wutsapper.transfer.feature.backup.step


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.FragmentStep3Binding
import com.wondershare.wutsapper.transfer.feature.backup.BackupViewmodel
import com.wondershare.wutsapper.transfer.feature.base.BaseFragment

class Step3Fragment : BaseFragment<FragmentStep3Binding, BackupViewmodel>() {
    private lateinit var binding: FragmentStep3Binding
    private lateinit var viewmodel: BackupViewmodel

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.fragment_step3
    override val mViewmodel: BackupViewmodel
        get() = ViewModelProvider(requireActivity())[BackupViewmodel::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!
        viewmodel = mViewmodel

        initView()
    }

    private fun initView() {
        viewmodel.backstack.postValue(3)
    }
}