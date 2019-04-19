package com.canker.data.response.tag.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Tag(
	@SerializedName("id") val id: String,
	@SerializedName("type") val type: String,
	@SerializedName("webTitle") val webTitle: String?,
	@SerializedName("webUrl") val webUrl: String?,
	@SerializedName("apiUrl") val apiUrl: String?,
	@SerializedName("activeSponsorships") val activeSponsorships: List<ActiveSponsorship>?,
	@SerializedName("paidContentType") val paidContentType: String
): Serializable
