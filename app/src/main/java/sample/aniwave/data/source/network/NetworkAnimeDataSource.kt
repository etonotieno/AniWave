/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.aniwave.data.source.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import sample.aniwave.data.source.AnimeDataSource
import sample.aniwave.data.source.network.model.NetworkAnime
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

    suspend fun searchAnime(photo: File): NetworkAnime? =
        searchApi.searchAnime(image = createFileMultipart(photo)).toNetworkAnime()

    private fun createFileMultipart(photo: File): MultipartBody.Part {
        val requestFile = photo.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", photo.name, requestFile)
    }
}
