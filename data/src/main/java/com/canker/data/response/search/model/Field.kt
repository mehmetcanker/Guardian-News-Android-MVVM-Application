package com.canker.data.response.search.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.io.Serializable

data class Field(
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("lastModified") val type: DateTime?,
    @SerializedName("headline") val headline: String?,
    @SerializedName("body") val body: String?
) : Serializable
