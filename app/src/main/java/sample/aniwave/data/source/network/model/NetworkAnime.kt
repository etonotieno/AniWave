package sample.aniwave.data.source.network.model

import sample.aniwave.data.source.local.model.LocalAnime

/**
 * Internal data model representing an [sample.aniwave.data.model.Anime] fetched from the Network
 *
 * @param episode The extracted episode number from filename. Episode can be null because it is
 * just a result of parsing the filename with [aniep](https://github.com/soruly/aniep)
 */
data class NetworkAnime(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val episode: Int,
)

fun NetworkAnime.toLocal(): LocalAnime {
    return LocalAnime(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        releaseYear = "2020s",
        rating = 0.0,
    )
}

fun List<NetworkAnime>.toLocal(): List<LocalAnime> = map(NetworkAnime::toLocal)
