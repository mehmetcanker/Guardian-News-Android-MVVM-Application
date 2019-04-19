package com.canker.data.response

import com.google.gson.annotations.SerializedName

open class BaseResponse<out T>(
    @SerializedName("response") val response: GenericResponse<T>? = null
)