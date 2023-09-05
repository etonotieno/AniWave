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
     * @param filename The filename of the file where the match is found
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
        @SerializedName("filename")
        val filename: String?,
        @SerializedName("from")
        val from: Double?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("similarity")
        val similarity: Double?,
        @SerializedName("to")
        val to: Double?,
        @SerializedName("video")
        val video: String?,
    ) {
        data class Anilist(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("idMal")
            val idMal: Int?,
            @SerializedName("isAdult")
            val isAdult: Boolean?,
            @SerializedName("synonyms")
            val synonyms: List<String?>?,
            @SerializedName("title")
            val title: Title?
        ) {
            data class Title(
                @SerializedName("english")
                val english: String?,
                @SerializedName("native")
                val native: String?,
                @SerializedName("romaji")
                val romaji: String?
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
        releaseYear = "",
        rank = 0,
    )
}
