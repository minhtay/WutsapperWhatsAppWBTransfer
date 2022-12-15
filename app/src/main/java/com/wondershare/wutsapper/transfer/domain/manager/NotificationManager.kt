package com.wondershare.wutsapper.transfer.domain.manager

import androidx.core.app.NotificationCompat

interface NotificationManager {

    fun cancelNotify(id: Long)

    fun createNotificationChannel(id: Long = 0L)

    fun buildNotificationChannelId(id: Long): String

    fun notifyDone(id: Long)

    fun getNotifyRecording(): NotificationCompat.Builder

    fun notifyFailed()

    fun notifyNoAvailableSpsace()

}