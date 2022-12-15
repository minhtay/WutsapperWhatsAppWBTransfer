package com.wondershare.wutsapper.transfer.feature.backup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wondershare.wutsapper.transfer.feature.base.BaseViewmodel

class BackupViewmodel : BaseViewmodel<BackupNavigator>() {

    var backstack = MutableLiveData<Int>()
    var nameToolbar = MutableLiveData<String>()
    var _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String>
        get() = _phoneNumber
    var _codePhone = MutableLiveData<String>()
    val codePhone: LiveData<String>
        get() = _codePhone
    var coutryPhone = MutableLiveData<String>()
    var statebar = MutableLiveData<Int>()

    init {
        _codePhone.postValue("1")
        coutryPhone.postValue("United States")
    }

}