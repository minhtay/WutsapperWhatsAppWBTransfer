package com.wondershare.wutsapper.transfer.feature.guide_backup

import androidx.lifecycle.MutableLiveData
import com.wondershare.wutsapper.transfer.feature.base.BaseNavigator
import com.wondershare.wutsapper.transfer.feature.base.BaseViewmodel

class GuideBackupViewmodel: BaseViewmodel<BaseNavigator>() {

    val page = MutableLiveData<Int>()

    init {
        page.postValue(0)
    }
}