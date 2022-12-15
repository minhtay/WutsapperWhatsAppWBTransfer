package com.wondershare.wutsapper.transfer.feature.user.status_saver

import androidx.lifecycle.MutableLiveData
import com.wondershare.wutsapper.transfer.feature.base.BaseNavigator
import com.wondershare.wutsapper.transfer.feature.base.BaseViewmodel

class StatusSaverViewmodel:BaseViewmodel<BaseNavigator>() {

    var type = MutableLiveData<Int>()

    init {
        type.postValue(1)
    }
}