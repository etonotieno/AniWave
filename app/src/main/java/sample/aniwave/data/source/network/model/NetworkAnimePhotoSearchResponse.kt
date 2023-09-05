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
        val anilist: Int?,
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
    )
}

fun NetworkAnimePhotoSearchResponse.Result?.getAnilistId(): Int = this?.anilist ?: 0

fun NetworkAnimePhotoSearchResponse.getAnilistId(): Int = getTopResult().getAnilistId()

/**
 * Get the Top Result of the most similar Anime
 */
fun NetworkAnimePhotoSearchResponse.getTopResult(): NetworkAnimePhotoSearchResponse.Result? {
    return this.result?.sortedByDescending { it.similarity }?.first()
}

fun NetworkAnimePhotoSearchResponse.toNetworkAnime(
    response: NetworkAnimeResponse.AnimeResponse?
): NetworkAnime? {
    // Override the Episode & imageUrl to return the data from AnimeSearchApi
    val result = getTopResult() ?: return null
    val item = response ?: return null
    return NetworkAnime(
        id = result.getAnilistId(),
        imageUrl = result.image.orEmpty(),
        episode = result.episode ?: 0,
        title = item.getEnglishTitle(),
        score = item.score ?: 0.0,
        releaseYear = "${item.getReleaseYear()}",
        rank = item.rank ?: 0,
    )
}
