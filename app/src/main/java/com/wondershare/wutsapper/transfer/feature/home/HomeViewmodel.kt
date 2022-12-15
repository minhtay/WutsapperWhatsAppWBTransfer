package com.wondershare.wutsapper.transfer.feature.home


import androidx.lifecycle.MutableLiveData
import com.wondershare.wutsapper.transfer.feature.base.BaseNavigator
import com.wondershare.wutsapper.transfer.feature.base.BaseViewmodel

class HomeViewmodel() : BaseViewmodel<BaseNavigator>() {

    val showMenu = MutableLiveData<Boolean>()
    val timeBackup = MutableLiveData<String>()
    val textDialogBackup = MutableLiveData<String>()
    val paramester = MutableLiveData<Int>()



    init {
        showMenu.postValue(false)
        timeBackup.postValue("20-11-2022 02:03:04")
        textDialogBackup.postValue("It is detected that the latest backup time was on ${timeBackup.value}. Use this backup data for transmission?")
    }



}