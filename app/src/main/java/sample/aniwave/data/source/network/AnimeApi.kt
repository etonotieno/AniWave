package sample.aniwave.data.source.network

import retrofit2.http.GET
import retrofit2.http.Query
import sample.aniwave.data.source.network.model.NetworkAnimeResponse

interface AnimeApi {

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 25,
    ): NetworkAnimeResponse
}
