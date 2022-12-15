package com.basic.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.basic.common.extension.tryOrNull
import com.basic.data.Preferences
import javax.inject.Inject

class Navigator @Inject constructor(
    private val context: Context,
    private val prefs: Preferences
) {

    private fun startActivity(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private fun startActivityExternal(intent: Intent) {
        if (intent.resolveActivity(context.packageManager) != null) {
            startActivity(intent)
        } else {
            startActivity(Intent.createChooser(intent, null))
        }
    }

    fun openPrivacy() {
        try {
            val intent =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
                    .apply {
                        data = Uri.parse("https://sites.google.com/view/lsvpn-privatepolicy")
                    }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
//            context.toast("Device not support, please check again!")
        }
    }

    fun openTermsOfService() {
        try {
            val intent =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
                    .apply {
                        data = Uri.parse("https://sites.google.com/view/lsvpn-terms-of-service")
                    }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
//            context.toast("Device not support, please check again!")
        }
    }

    fun showSupport() {
//        analyticManager.logEvent(AnalyticManager.TYPE.CLICKED_SUPPORT, "Clicked Support")
//        val intent = Intent(Intent.ACTION_SENDTO)
//        intent.data = Uri.parse("mailto:")
//        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("sola.luthimylanh@gmail.com"))
//        intent.putExtra(Intent.EXTRA_SUBJECT, "LsVpn Support")
//        intent.putExtra(
//            Intent.EXTRA_TEXT, StringBuilder("\n\n")
//                .append("\n\n--- Please write your message above this line ---\n\n")
//                .append("Package: ${context.packageName}\n")
//                .append("Version: ${BuildConfig.VERSION_NAME}\n")
//                .append("Device: ${Build.BRAND} ${Build.MODEL}\n")
//                .append("SDK: ${Build.VERSION.SDK_INT}\n")
//                .append("Upgraded: ${prefs.isUpgraded.get()}")
//                .toString()
//        )
//        startActivityExternal(intent)
    }

    fun showInvite() {
//        analyticManager.logEvent(AnalyticManager.TYPE.CLICKED_INVITE, "Clicked Invite")
        Intent(Intent.ACTION_SEND)
            .setType("text/plain")
//            .putExtra(
//                Intent.EXTRA_TEXT,
//                "http://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
//            )
            .let { Intent.createChooser(it, null) }
            .let(::startActivityExternal)
    }

    fun showRating() {
//        analyticManager.logEvent(AnalyticManager.TYPE.CLICKED_RATING, "Clicked Rating")
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${BuildConfig.APPLICATION_ID}"))
//            .addFlags(
//                Intent.FLAG_ACTIVITY_NO_HISTORY
//                        or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
//                        or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
//            )
//        try {
//            startActivityExternal(intent)
//        } catch (e: ActivityNotFoundException) {
//            val url = "http://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
//            startActivityExternal(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
//        }
    }

    fun openBrowser(url: String) {
//        analyticManager.logEvent(AnalyticManager.TYPE.OPEN_APPS, "Clicked $url")
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
            .addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY
                        or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                        or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )

        try {
            startActivityExternal(intent)
        } catch (e: ActivityNotFoundException) {
            startActivityExternal(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    fun showNotificationManager() {
        tryOrNull {
            val intent = Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            intent.putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, context.packageName)
            startActivity(intent)
        }
    }

}