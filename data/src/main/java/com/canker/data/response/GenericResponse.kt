package com.canker.data.response

import com.google.gson.annotations.SerializedName

open class GenericResponse<out T>(
    @SerializedName("status") val status: String? = null,
    @SerializedName("userTier") val userTier: String? = null,
    @SerializedName("total") val total: Int? = 1,
    @SerializedName("startIndex") val startIndex: Int? = 1,
    @SerializedName("pageSize") val pageSize: Int? = 1,
    @SerializedName("currentPage") val currentPage: Int? = 1,
    @SerializedName("orderBy") val orderBy: String? = null,
    @SerializedName("pages") val pages: Int? = 1,
    @SerializedName("results") val results: List<T>? = listOf()
)