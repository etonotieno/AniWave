package sample.aniwave.data.source.network

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import sample.aniwave.data.source.network.model.NetworkAnimePhotoSearchResponse

interface AnimeSearchApi {

    @Multipart
    @POST("search")
    suspend fun searchAnime(
        @Part image: MultipartBody.Part,
    ): NetworkAnimePhotoSearchResponse
}
