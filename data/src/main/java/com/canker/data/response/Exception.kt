package com.canker.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Exception(
        @SerializedName("Message") val message: String?
): Serializable