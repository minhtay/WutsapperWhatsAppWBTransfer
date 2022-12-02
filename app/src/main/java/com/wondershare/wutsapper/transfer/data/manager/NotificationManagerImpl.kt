package com.wondershare.wutsapper.transfer.data.manager

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.wondershare.wutsapper.transfer.R
import com.basic.data.Preferences
import com.wondershare.wutsapper.transfer.domain.manager.NotificationManager
import com.basic.common.ConfigApp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationManagerImpl @Inject constructor(
    private val context: Context,
    private val prefs: Preferences,
    private val configApp: ConfigApp
) : NotificationManager {

    companion object {
        const val DEFAULT_CHANNEL_ID = "notifications_default"
        const val RECORDING_CHANNEL_ID = "notifications_recording"

        val VIBRATE_PATTERN = longArrayOf(0, 200, 0, 200)
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager?

    init {
        createNotificationChannel()
    }

    override fun cancelNotify(id: Long) {
        notificationManager?.cancel(id.toInt())
    }

    override fun createNotificationChannel(id: Long) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || getNotificationChannel(id) != null) {
            return
        }

        val channel = when (id) {
            0L -> NotificationChannel(
                DEFAULT_CHANNEL_ID,
                "Default",
                android.app.NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                lightColor = Color.WHITE
                enableVibration(true)
                vibrationPattern = VIBRATE_PATTERN
            }
            else -> {
//                val record = recordDao.findById(id)

                val channelId = buildNotificationChannelId(id)
//                val title = record?.name ?: ""
                NotificationChannel(
                    channelId,
                    "title",
                    android.app.NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    enableLights(true)
                    lightColor = Color.WHITE
                    enableVibration(true)
                    vibrationPattern = VIBRATE_PATTERN
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }
            }
        }

        notificationManager?.createNotificationChannel(channel)
    }

    private fun getNotificationChannel(
        id: Long,
        isAutoReply: Boolean = false
    ): NotificationChannel? {
        val channelId = buildNotificationChannelId(id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return notificationManager?.notificationChannels?.find { channel -> channel.id == channelId }
        }

        return null
    }

    override fun buildNotificationChannelId(id: Long): String {
        return when {
            id == 0L -> DEFAULT_CHANNEL_ID
            else -> "notifications_$id"
        }
    }

    private fun getChannelIdForNotification(id: Long): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return getNotificationChannel(id)?.id ?: DEFAULT_CHANNEL_ID
        }

        return DEFAULT_CHANNEL_ID
    }

    override fun notifyDone(id: Long) {
//        if (!prefs.notifications(id).get()) {
//            return
//        }

//        val scheduleTask = scheduleTaskDao.getWithId(id) ?: return

//        val contentIntent = Intent(context, DetailActivity::class.java)
//        contentIntent.putExtra(ConfigApp.ID_SCHEDULE_TASK_EXTRA, id)
//        contentIntent.putExtra(ConfigApp.IS_NOTIFICATION_EXTRA, true)
//
//        val taskStackBuilder = TaskStackBuilder.create(context)
//            .addParentStack(DetailActivity::class.java)
//            .addNextIntent(contentIntent)
//
//        val contentPI = taskStackBuilder.getPendingIntent(
//            id.toInt(),
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val ringtone = prefs.ringtone(id).get()
//            .takeIf { it.isNotEmpty() }
//            ?.let(Uri::parse)
//            ?.also { uri ->
//                context.grantUriPermission(
//                    "com.android.systemui",
//                    uri,
//                    Intent.FLAG_GRANT_READ_URI_PERMISSION
//                )
//            }
//
//        val notification = NotificationCompat.Builder(context, getChannelIdForNotification(id))
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentText(scheduleTask.getDisplayTitle() + "\n• ${scheduleTask.getFormatContent(configApp, context)}")
//            .setAutoCancel(true)
//            .setContentIntent(contentPI)
//            .setSound(ringtone)
//            .setLights(Color.WHITE, 500, 2000)
//            .setWhen(System.currentTimeMillis())
//            .setVibrate(
//                if (prefs.vibration(id).get()) VIBRATE_PATTERN else longArrayOf(0)
//            )
//
//        when (scheduleTask.status) {
//            ScheduleStatus.Failed -> notification.setContentTitle("Message sent failed!")
//            ScheduleStatus.Canceled -> notification.setContentTitle("Message has been cancelled")
//            else -> notification.setContentTitle("Message sent successfully!")
//        }
//
//        val activitySchedule = when (scheduleTask.scheduleType) {
//            ScheduleType.Sms -> ScheduleSmsActivity::class.java
//            ScheduleType.Reminder -> ScheduleReminderActivity::class.java
//            ScheduleType.Gmail -> ScheduleGmailActivity::class.java
//            ScheduleType.FakeCall -> FakeCallActivity::class.java
//            ScheduleType.Call -> ScheduleCallActivity::class.java
//        }
//
//        val intent = Intent(context, activitySchedule)
//        val pi = PendingIntent.getActivity(context, id.toInt(), intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
//        notification.addAction(NotificationCompat.Action.Builder(null, "New timer message", pi).build())
//
//        notificationManager?.notify(id.toInt(), notification.build())
//
//        // Wake screen
//        if (prefs.wakeScreen(id).get()) {
//            context.getSystemService<PowerManager>()?.let { powerManager ->
//                if (!powerManager.isInteractive) {
//                    val flags =
//                        PowerManager.SCREEN_DIM_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP
//                    val wakeLock = powerManager.newWakeLock(flags, context.packageName)
//                    wakeLock.acquire(5000)
//                }
//            }
//        }
    }

    override fun notifyFailed() {
//        val id = (0..1000).random()
//
//        val contentIntent = Intent(context, MainActivity::class.java)
//
//        val taskStackBuilder = TaskStackBuilder.create(context)
//            .addParentStack(MainActivity::class.java)
//            .addNextIntent(contentIntent)
//
//        val contentPI = taskStackBuilder.getPendingIntent(
//            id,
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val notification = NotificationCompat.Builder(context, getChannelIdForNotification(id.toLong()))
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle("Recording error")
//            .setContentText("An error occurred, please try again!")
//            .setAutoCancel(true)
//            .setContentIntent(contentPI)
//            .setWhen(System.currentTimeMillis())
//
//        notificationManager?.notify(id, notification.build())
    }

    override fun notifyNoAvailableSpsace() {
//        val id = (0..1000).random()
//
//        val contentIntent = Intent(context, MainActivity::class.java)
//
//        val taskStackBuilder = TaskStackBuilder.create(context)
//            .addParentStack(MainActivity::class.java)
//            .addNextIntent(contentIntent)
//
//        val contentPI = taskStackBuilder.getPendingIntent(
//            id,
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val notification = NotificationCompat.Builder(context, getChannelIdForNotification(id.toLong()))
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle("Recording error")
//            .setContentText("No available space, please try again!")
//            .setAutoCancel(true)
//            .setContentIntent(contentPI)
//            .setWhen(System.currentTimeMillis())
//
//        notificationManager?.notify(id, notification.build())
    }

    override fun getNotifyRecording(): NotificationCompat.Builder {
        if (Build.VERSION.SDK_INT >= 26) {
            val importance = android.app.NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(RECORDING_CHANNEL_ID, RECORDING_CHANNEL_ID, importance).apply {
                setSound(null, null)
            }
            val notificationManager = context.getSystemService(android.app.NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(context, RECORDING_CHANNEL_ID)
            .setShowWhen(false)
            .setContentTitle("Recording")
            .setContentText("Tap to stop recording...")
            .setWhen(System.currentTimeMillis()) // Set this anyway in case it's shown
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(false)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSound(null)
    }


}