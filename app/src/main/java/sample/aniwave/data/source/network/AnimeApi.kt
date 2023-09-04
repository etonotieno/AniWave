package sample.aniwave.data.source.network

import retrofit2.http.GET
import retrofit2.http.Path
import sample.aniwave.data.source.network.model.NetworkAnimeIdResponse
import sample.aniwave.data.source.network.model.NetworkAnimeResponse

interface AnimeApi {

    @GET("top/anime")
    suspend fun getTopAnime(): NetworkAnimeResponse

    @GET("anime/{id}")
    suspend fun getAnime(@Path("id") id: Int): NetworkAnimeIdResponse
}
