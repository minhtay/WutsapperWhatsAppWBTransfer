package com.wondershare.wutsapper.transfer.feature.backup.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wondershare.wutsapper.transfer.feature.backup.step.DeviceConnectionFragment

class BackupAdapterViewpagerOption2(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf(DeviceConnectionFragment())
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}