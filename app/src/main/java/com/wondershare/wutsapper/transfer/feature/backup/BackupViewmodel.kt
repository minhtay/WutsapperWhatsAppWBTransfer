package com.wondershare.wutsapper.transfer.feature.backup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wondershare.wutsapper.transfer.feature.base.BaseNavigator
import com.wondershare.wutsapper.transfer.feature.base.BaseViewmodel

class BackupViewmodel : BaseViewmodel<BaseNavigator>() {

    var backstack = MutableLiveData<Int>()
    var nameToolbar = MutableLiveData<String>()
    var _phoneNumber = MutableLiveData<String>()
    var _codePhone = MutableLiveData<String>()
    var coutryPhone = MutableLiveData<String>()
    var statebar = MutableLiveData<Int>()
    var spinnerCheck = MutableLiveData<Boolean>()
    var spinnercode = MutableLiveData<Int>()

    init {
        spinnerCheck.postValue(false)
        spinnercode.postValue(1)
        _codePhone.postValue("1")
        coutryPhone.postValue("United States")
    }

}