package id.tiooooo.myarticle.data.implementation.remote.response

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("version") val version: String?,
    @SerializedName("news_sites") val newsSite: List<String>?,
)