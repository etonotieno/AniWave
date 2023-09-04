package sample.aniwave.data.source.network

import retrofit2.http.GET
import sample.aniwave.data.source.network.model.NetworkAnimeResponse

interface AnimeApi {

    @GET("top/anime")
    suspend fun getTopAnime(): NetworkAnimeResponse
}
