package sample.aniwave.data.source.network

import retrofit2.http.GET
import retrofit2.http.Query
import sample.aniwave.data.source.network.model.NetworkAnimeResponse

interface AnimeApi {

    /**
     * Fetch the top anime. Fetches the top 25 anime by default. The size of the result is depends
     * on [count]
     */
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("limit") count: Int = 25,
    ): NetworkAnimeResponse
}
