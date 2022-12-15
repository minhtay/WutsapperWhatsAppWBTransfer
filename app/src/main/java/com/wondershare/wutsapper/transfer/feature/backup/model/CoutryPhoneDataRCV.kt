package com.wondershare.wutsapper.transfer.feature.backup.model

import java.io.Serializable

data class CoutryPhoneDataRCV(
    val code: String,
    val country: String,
    val iso: String
) : Serializable