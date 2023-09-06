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
package sample.aniwave.data.source.network.model

import com.google.gson.annotations.SerializedName

/**
 * @param frameCount Total number of frames searched
 * @param result Anime Search results based on the uploaded photo. Results are sorted from most
 * similar to least similar
 */
data class NetworkAnimePhotoSearchResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("frameCount")
    val frameCount: Int?,
    @SerializedName("result")
    val result: List<Result>?,
) {
    /**
     *
     * @param episode The extracted episode number from filename. Episode can be null because it is
     * just a result of parsing the filename with [aniep](https://github.com/soruly/aniep)
     * @param anilist The matching Anilist ID or Anilist info. (Can be an Int or Object/Any)
     * @param similarity Similarity compared to the search image. Values are in the range of 0-1.
     * Similarity <90% (0.9) are most likely incorrect results
     * @param image URL to the preview image of the matching scene
     */
    data class Result(
        @SerializedName("anilist")
        val anilist: Anilist?,
        @SerializedName("episode")
        val episode: Int?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("similarity")
        val similarity: Double?,
    ) {
        data class Anilist(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("idMal")
            val idMal: Int?,
            @SerializedName("title")
            val title: Title?,
        ) {
            data class Title(
                @SerializedName("english")
                val english: String?,
                @SerializedName("native")
                val native: String?,
                @SerializedName("romaji")
                val romaji: String?,
            )
        }
    }
}

/**
 * Get the Top Result of the most similar Anime
 */
fun NetworkAnimePhotoSearchResponse.getTopResult(): NetworkAnimePhotoSearchResponse.Result? {
    return this.result?.sortedByDescending { it.similarity }?.first()
}

fun NetworkAnimePhotoSearchResponse.toNetworkAnime(): NetworkAnime? {
    val result = getTopResult() ?: return null
    val anilist = result.anilist
    return NetworkAnime(
        id = anilist?.idMal ?: 0,
        imageUrl = result.image.orEmpty(),
        episode = result.episode ?: 0,
        title = anilist?.title?.english.orEmpty(),
        score = 0.0,
        releaseYear = 2023,
        rank = 0,
    )
}
