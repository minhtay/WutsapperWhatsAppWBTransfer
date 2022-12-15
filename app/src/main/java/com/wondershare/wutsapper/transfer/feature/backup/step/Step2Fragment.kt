package com.wondershare.wutsapper.transfer.feature.backup.step

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.wondershare.wutsapper.transfer.BR
import com.wondershare.wutsapper.transfer.R
import com.wondershare.wutsapper.transfer.databinding.FragmentStep2Binding
import com.wondershare.wutsapper.transfer.feature.backup.BackupActivity
import com.wondershare.wutsapper.transfer.feature.backup.BackupViewmodel
import com.wondershare.wutsapper.transfer.feature.backup.adapter.Step2AdapterRCV
import com.wondershare.wutsapper.transfer.feature.backup.model.Step2DataRCV
import com.wondershare.wutsapper.transfer.feature.base.BaseFragment

class Step2Fragment : BaseFragment<FragmentStep2Binding, BackupViewmodel>() {

    private val activity by lazy { requireActivity() as BackupActivity }
    private lateinit var binding: FragmentStep2Binding
    private lateinit var viewmodel: BackupViewmodel

    override val bindingVariable: Int
        get() = BR.viewmodel
    override val layoutId: Int
        get() = R.layout.fragment_step2
    override val mViewmodel: BackupViewmodel
        get() = activity.mViewmodel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!
        viewmodel = mViewmodel

        initView()
        actionView()
    }

    private fun initView() {
        viewmodel.backstack.postValue(2)
        viewmodel.nameToolbar.postValue("+${viewmodel._codePhone.value} ${viewmodel.phoneNumber.value}")
        viewmodel.statebar.postValue(2)

        binding.rcvStep2.apply {
            adapter = Step2AdapterRCV(arrayStep2RCV())
            layoutManager = GridLayoutManager(activity,3)
            suppressLayout(true)
        }
    }

    private fun actionView() {


    }

    private fun arrayStep2RCV():ArrayList<Step2DataRCV>{
        val arrayList = ArrayList<Step2DataRCV>()
        arrayList.add(Step2DataRCV(R.drawable.icon_text,"Text","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_image,"Images","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_video,"Videos","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_audio,"Audios","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_files,"Files","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_emoji,"Emoji","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_location,"Locations","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_gif,"Gifs","1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_voice,"Voices","1"))
        return arrayList
    }

    override fun onDestroy() {
        super.onDestroy()
        viewmodel.backstack.postValue(1)
    }



}