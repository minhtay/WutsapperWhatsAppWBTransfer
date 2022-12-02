package com.wondershare.wutsapper.transfer.domain.interactor

import android.content.Context
import com.basic.common.ConfigApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.reactivex.Flowable
import javax.inject.Inject

class SyncFirebaseConfig @Inject constructor(
    private val context: Context,
    private val configApp: ConfigApp
) : Interactor<Unit>() {

    override fun buildObservable(params: Unit): Flowable<*> {
        return Flowable.just(System.currentTimeMillis())
            .doOnNext { syncFirebaseConfig() }
    }

    private fun syncFirebaseConfig(number: Int = 3) {
        val config = FirebaseRemoteConfig.getInstance()
        config.fetchAndActivate().addOnSuccessListener {
            when (number) {
                in 1 .. 3 -> syncFirebaseConfig(number - 1)
                else -> {

                }
            }
        }.addOnFailureListener {

        }
    }


}