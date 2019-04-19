@file :Suppress("MissingPermission", "HardwareIds")

package com.canker.core.utils


import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import java.util.*

object DeviceUtils {

    val deviceName: String
        get() {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                capitalizeFirstLetter(model)
            } else {
                capitalizeFirstLetter(manufacturer) + " " + model
            }
        }

    val releaseVersion: String
        get() = Build.VERSION.RELEASE

    val brand: String
        get() = Build.BRAND

    val model: String
        get() = Build.MODEL

    private fun capitalizeFirstLetter(s: String?): String {
        if (s == null || s.isEmpty()) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first) + s.substring(1)
        }
    }

    fun getAppVersion(context: Context): String? {
        val pInfo: PackageInfo
        var version = "0.0.0"
        try {
            pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            version = pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return null
        }

        return version
    }

    @Suppress("DEPRECATION")
    fun getIMEI(context: Application): String {
        val mTelephonyMgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var imei: String? =
            mTelephonyMgr.deviceId
        if (imei == null || imei.length == 0) {
            imei = getUDID(context)
        }
        return imei
    }


    @Suppress("DEPRECATION")
    private fun getUDID(context: Application): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val tmDevice = "" + tm.deviceId
        val tmSerial = "" + tm.simSerialNumber
        val androidId = "" + Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        val deviceUuid =
            UUID(androidId.hashCode().toLong(), tmDevice.hashCode().toLong() shl 32 or tmSerial.hashCode().toLong())
        return deviceUuid.toString()
    }

    fun getAndroidId(application: Application): String {
        return Settings.Secure.getString(application.contentResolver, Settings.Secure.ANDROID_ID)
    }
}
