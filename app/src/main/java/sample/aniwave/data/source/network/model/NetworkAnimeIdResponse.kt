package sample.aniwave.data.source.network.model

import com.google.gson.annotations.SerializedName

data class NetworkAnimeIdResponse(
    @SerializedName("data")
    val result: NetworkAnimeResponse.AnimeResponse?,
)
