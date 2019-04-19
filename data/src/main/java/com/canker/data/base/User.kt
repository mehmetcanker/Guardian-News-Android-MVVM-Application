package com.canker.data.base

import com.google.gson.annotations.SerializedName

data class LoggedUser(
        @SerializedName("Id") val id: Int?,
        @SerializedName("Username") var username: String?,
        @SerializedName("Email") var email: String?
)

data class Settings(@SerializedName("LanguageCode") var languageCode: String?)
