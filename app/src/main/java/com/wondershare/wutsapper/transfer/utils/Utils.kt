package com.wondershare.wutsapper.transfer.utils

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.wondershare.wutsapper.transfer.feature.backup.model.CoutryPhoneDataRCV
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset


object Utils {

    fun getBatteryLevel(context: Context, intent: Intent?): Float {
        val batteryStatus: Intent? = context.registerReceiver(
            null, IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
        var batteryLevel = -1
        var batteryScale = 1
        if (batteryStatus != null) {
            batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, batteryLevel)
            batteryScale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, batteryScale)
        }
        return batteryLevel / batteryScale.toFloat() * 100
    }

    fun loadJSONFromAsset(context: Context): ArrayList<CoutryPhoneDataRCV>? {
        val json: String?
        val arrayList = ArrayList<CoutryPhoneDataRCV>()
        try {
            val inputStream = context.assets.open("CoutryPhoneCode.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
            val array = JSONArray(json)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                arrayList.add(
                    CoutryPhoneDataRCV(
                        code = obj.getString("code"),
                        country = obj.getString("country"),
                        iso = obj.getString("iso")
                    )
                )
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return arrayList
    }

}