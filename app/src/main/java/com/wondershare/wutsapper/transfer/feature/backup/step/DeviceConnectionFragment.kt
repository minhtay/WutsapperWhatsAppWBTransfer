package com.wondershare.wutsapper.transfer.feature.backup.step

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.FragmentDeviceConnectionBinding
import com.wondershare.wutsapper.transfer.feature.backup.BackupActivity
import com.wondershare.wutsapper.transfer.feature.backup.BackupViewmodel
import com.wondershare.wutsapper.transfer.feature.base.BaseFragment
import com.wondershare.wutsapper.transfer.feature.backup.share_file.ShareFileActivity

class DeviceConnectionFragment : BaseFragment<FragmentDeviceConnectionBinding, BackupViewmodel>() {

    private val activity by lazy { requireActivity() as BackupActivity }
    private lateinit var binding: FragmentDeviceConnectionBinding
    private lateinit var viewmodel: BackupViewmodel

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.fragment_device_connection
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
        viewmodel.nameToolbar.postValue(resources.getString(R.string.device_connection))
        viewmodel.statebar.postValue(2)
    }

    private fun actionView() {
        binding.tranferOption1.view.setOnClickListener {
            ShareFileActivity.startActivity(activity)
        }

        binding.tranferOption2.view.setOnClickListener {
        }
    }

}