package com.wondershare.wutsapper.transfer.feature.backup.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wondershare.wutsapper.transfer.feature.backup.step.DeviceConnectionFragment
import com.wondershare.wutsapper.transfer.feature.backup.step.Step1Fragment
import com.wondershare.wutsapper.transfer.feature.backup.step.Step2Fragment

class BackupAdapterViewPager(private val fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf(Step1Fragment(), Step2Fragment())
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}