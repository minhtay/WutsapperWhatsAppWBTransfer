package com.wondershare.wutsapper.transfer.feature.backup.step

import android.os.Bundle
import android.util.Log
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
    private lateinit var adapter: Step2AdapterRCV
    private var itemCheck: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)

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

    override fun onResume() {
        super.onResume()
        initView()
        actionView()
        itemCheck = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        binding.unselectAll.text = resources.getString(R.string.unselect_All)
    }

    private fun initView() {
        viewmodel.backstack.postValue(2)
        viewmodel.nameToolbar.postValue(resources.getString(R.string.wa_data))
        viewmodel.statebar.postValue(2)

        // setup recyclerview
        binding.rcvStep2.layoutManager = GridLayoutManager(activity, 3)
        adapter = Step2AdapterRCV(arrayStep2RCV(), true)
        binding.rcvStep2.adapter = adapter
        binding.rcvStep2.suppressLayout(true)

        itemClickListener(adapter)
    }

    private fun actionView() {
        binding.unselectAll.setOnClickListener {
            if (itemCheck.size == 9) {
                adapter = Step2AdapterRCV(arrayStep2RCV(), false)
                binding.rcvStep2.adapter = adapter
                itemCheck = ArrayList<Int>()
                itemClickListener(adapter)
                binding.unselectAll.text = resources.getString(R.string.select_All)
            } else {
                adapter = Step2AdapterRCV(arrayStep2RCV(), true)
                binding.rcvStep2.adapter = adapter
                itemCheck = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
                itemClickListener(adapter)
                binding.unselectAll.text = resources.getString(R.string.unselect_All)
            }
        }
    }

    private fun itemClickListener(adapter: Step2AdapterRCV) {
        adapter.itemClick = {
            if (it in itemCheck) {
                itemCheck.removeAll { value -> value == it }
                binding.unselectAll.text = resources.getString(R.string.select_All)
            } else {
                itemCheck.add(it)
                if (itemCheck.size <= 8) {
                    binding.unselectAll.text = resources.getString(R.string.select_All)
                } else binding.unselectAll.text = resources.getString(R.string.unselect_All)
            }
        }
    }


    private fun arrayStep2RCV(): ArrayList<Step2DataRCV> {
        val arrayList = ArrayList<Step2DataRCV>()
        arrayList.add(Step2DataRCV(R.drawable.icon_text, "Text", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_image, "Images", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_video, "Videos", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_audio, "Audios", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_files, "Files", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_emoji, "Emoji", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_location, "Locations", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_gif, "Gifs", "1"))
        arrayList.add(Step2DataRCV(R.drawable.icon_voice, "Voices", "1"))
        return arrayList
    }

    override fun onDestroy() {
        super.onDestroy()
        viewmodel.backstack.postValue(1)
    }


}