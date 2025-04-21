package id.tiooooo.myarticle.utils.wrapper

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<out T>(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: T?
)