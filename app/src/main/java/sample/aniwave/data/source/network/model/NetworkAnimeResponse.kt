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

data class NetworkAnimeResponse(
    @SerializedName("data")
    val anime: List<AnimeResponse>?,
) {
    data class AnimeResponse(
        @SerializedName("aired")
        val aired: Aired,
        @SerializedName("episodes")
        val episodes: Int?,
        @SerializedName("images")
        val images: Images?,
        @SerializedName("mal_id")
        val malId: Int?,
        @SerializedName("rank")
        val rank: Int?,
        @SerializedName("score")
        val score: Double?,
        @SerializedName("titles")
        val titles: List<Title>?,
        @SerializedName("year")
        val year: Int?,
    ) {
        data class Aired(
            @SerializedName("from")
            val from: String?,
            @SerializedName("prop")
            val prop: Prop?,
            @SerializedName("string")
            val string: String?,
            @SerializedName("to")
            val to: String?,
        ) {
            data class Prop(
                @SerializedName("from")
                val from: From?,
                @SerializedName("to")
                val to: To?,
            ) {
                data class From(
                    @SerializedName("day")
                    val day: Int?,
                    @SerializedName("month")
                    val month: Int?,
                    @SerializedName("year")
                    val year: Int?,
                )

                data class To(
                    @SerializedName("day")
                    val day: Int?,
                    @SerializedName("month")
                    val month: Int?,
                    @SerializedName("year")
                    val year: Int?,
                )
            }
        }

        data class Images(
            @SerializedName("jpg")
            val jpg: Jpg?,
            @SerializedName("webp")
            val webp: Webp?,
        ) {
            data class Jpg(
                @SerializedName("image_url")
                val imageUrl: String?,
                @SerializedName("large_image_url")
                val largeImageUrl: String?,
                @SerializedName("small_image_url")
                val smallImageUrl: String?,
            )

            data class Webp(
                @SerializedName("image_url")
                val imageUrl: String?,
                @SerializedName("large_image_url")
                val largeImageUrl: String?,
                @SerializedName("small_image_url")
                val smallImageUrl: String?,
            )
        }

        data class Title(
            @SerializedName("title")
            val title: String?,
            @SerializedName("type")
            val type: String?,
        )
    }
}

fun NetworkAnimeResponse.AnimeResponse.toNetworkAnime(): NetworkAnime {
    return NetworkAnime(
        id = this.malId ?: 0,
        imageUrl = this.images?.jpg?.imageUrl.orEmpty(),
        title = this.getEnglishTitle(),
        episode = this.episodes ?: 0,
        score = this.score ?: 0.0,
        releaseYear = this.getReleaseYear(),
        rank = this.rank ?: 0,
    )
}

fun NetworkAnimeResponse.AnimeResponse.getReleaseYear(): Int {
    return this.year ?: this.aired.prop?.from?.year ?: 0
}

fun NetworkAnimeResponse.AnimeResponse.getEnglishTitle(): String =
    this.titles?.find { title -> title.type == "English" }?.title.orEmpty()

fun List<NetworkAnimeResponse.AnimeResponse>.toNetworkAnime(): List<NetworkAnime> =
    map { it.toNetworkAnime() }
