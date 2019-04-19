package com.canker.data.response.tag.model

import com.canker.data.base.Size
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.io.Serializable

data class ActiveSponsorship(
    @SerializedName("sponsorshipType") val sponsorshipType: String?,
    @SerializedName("sponsorName") val sponsorName: String?,
    @SerializedName("sponsorLogo") val sponsorLogo: String?,
    @SerializedName("sponsorLink") val sponsorLink: String?,
    @SerializedName("sponsorLogoDimensions") val sponsorLogoDimensions: Size?,
    @SerializedName("validFrom") val validFrom: DateTime?,
    @SerializedName("validTo") val validTo: DateTime?
) : Serializable