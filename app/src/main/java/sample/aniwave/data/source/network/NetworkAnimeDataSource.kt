package sample.aniwave.data.source.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import sample.aniwave.data.source.AnimeDataSource
import sample.aniwave.data.source.network.model.NetworkAnime
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
        return animeApi.getTopAnime().anime.orEmpty().map {
            NetworkAnime(
                id = it.malId ?: -1,
                imageUrl = it.images?.jpg?.imageUrl ?: "",
                title = it.titles?.find { title -> title.type == "English" }?.title ?: "",
                episode = it.episodes ?: -1,
            )
        }
    }

    suspend fun searchAnime(photo: File): NetworkAnime {
        val requestFile = photo.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val photoPart = MultipartBody.Part.createFormData("image", photo.name, requestFile)

        val result = searchApi.searchAnime(image = photoPart).result
            ?.first()

        return NetworkAnime(
            id = result?.anilist as? Int ?: -1,
            imageUrl = result?.image ?: "",
            title = result?.filename ?: "",
            episode = result?.episode as? Int ?: -1,
        )
    }
}
