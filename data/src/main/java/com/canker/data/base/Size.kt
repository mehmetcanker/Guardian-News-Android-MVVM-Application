package com.canker.data.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Size(
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?
): Serializable