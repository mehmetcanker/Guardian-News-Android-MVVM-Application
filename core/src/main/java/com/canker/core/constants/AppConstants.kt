package com.canker.core.constants

import com.canker.core.BuildConfig


object AppConstants {

    const val API_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.GUARDIAN_KEY
    const val NETWORK_TIMEOUT: Long = 10
    const val MAX_MEMORY_CACHE: Long = 20 * 1024 * 1024
    const val DB_NAME = BuildConfig.DATABASE_NAME
    const val API_KEY_NAME = "api-key"

}
