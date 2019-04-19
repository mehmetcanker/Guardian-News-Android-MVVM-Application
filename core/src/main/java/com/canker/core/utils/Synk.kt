package com.canker.core.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.concurrent.TimeUnit


object Synk {

    private var preferences: SharedPreferences? = null
    private const val SYNK_IT = true
    private const val DONT_SYNK = false

    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun shouldSync(key: String, window: Int = 4, unit: TimeUnit = TimeUnit.HOURS): Boolean {
        performPrefsSanityCheck()
        if (unit == TimeUnit.MILLISECONDS
            || unit == TimeUnit.NANOSECONDS
            || unit == TimeUnit.MICROSECONDS
            || unit == TimeUnit.SECONDS
        )
            throw IllegalStateException("Illegal time window")
        val currentSavedValue = preferences?.getString(key, "")
        if (currentSavedValue.isNullOrEmpty())
            return syncIt(key)
        val syncedTime = DateTime.parse(currentSavedValue)
        val syncBlock = when (unit) {
            TimeUnit.MINUTES -> syncedTime.plusMinutes(window)
            TimeUnit.HOURS -> syncedTime.plusHours(window)
            TimeUnit.DAYS -> syncedTime.plusDays(window)
            else -> syncedTime
        }
        return if (DateTime.now() >= syncBlock) syncIt(key) else DONT_SYNK
    }

    fun syncSuccess(key: String) {
        performPrefsSanityCheck()
        saveSyncTime(key)
    }


    fun syncFailure(key: String) {
        performPrefsSanityCheck()
        preferences
            ?.edit()
            ?.remove(key)
            ?.apply()
    }


    private fun performPrefsSanityCheck() {
        if (preferences == null)
            throw IllegalStateException("Synk Exception")
    }


    private fun syncIt(key: String): Boolean {
        saveSyncTime(key)
        return SYNK_IT
    }

    private fun saveSyncTime(key: String, dateTime: DateTime = DateTime.now()) {
        preferences
            ?.edit()
            ?.putString(key, ISODateTimeFormat.dateTime().print(dateTime))
            ?.apply()
    }
}
