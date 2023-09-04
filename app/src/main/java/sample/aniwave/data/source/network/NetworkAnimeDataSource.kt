package sample.aniwave.data.source.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import sample.aniwave.data.source.AnimeDataSource
import sample.aniwave.data.source.network.model.NetworkAnime
import sample.aniwave.data.source.network.model.getAnilistId
import sample.aniwave.data.source.network.model.getTopResult
import sample.aniwave.data.source.network.model.toNetworkAnime
import java.io.File
import javax.inject.Inject

/**
 * [NetworkAnime] data source backed by [AnimeApi] & [AnimeSearchApi]
 *
 * Data Sources are responsible for one type of data
 */
class NetworkAnimeDataSource @Inject constructor(
    private val animeApi: AnimeApi,
    private val searchApi: AnimeSearchApi,
) : AnimeDataSource {

    suspend fun getTopAnime(): List<NetworkAnime> {
        return animeApi.getTopAnime().anime.orEmpty().sortedBy { it.rank ?: 0 }.toNetworkAnime()
    }

    suspend fun searchAnime(photo: File): NetworkAnime? {
        val result =
            searchApi.searchAnime(image = createFileMultipart(photo)).getTopResult()
        return animeApi.getAnime(result.getAnilistId()).result?.toNetworkAnime()
            // Override the Episode & imageUrl to return the data from AnimeSearchApi
            ?.copy(episode = result?.episode ?: 0, imageUrl = result?.image.orEmpty())
    }

    private fun createFileMultipart(photo: File): MultipartBody.Part {
        val requestFile = photo.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", photo.name, requestFile)
    }
}
