package sample.aniwave.data.source.network.model

import com.google.gson.annotations.SerializedName

/**
 * An error response returned by [sample.aniwave.data.source.network.AnimeApi]
 */
data class AnimeApiError(
    @SerializedName("error")
    val error: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("report_url")
    val reportUrl: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("type")
    val type: String?,
)
