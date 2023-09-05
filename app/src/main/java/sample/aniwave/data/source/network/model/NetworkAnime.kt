package sample.aniwave.data.source.network.model

import sample.aniwave.data.model.Anime
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
    val score: Double,
    val releaseYear: Int,
    val rank: Int,
)

fun NetworkAnime.toLocal(): LocalAnime {
    return LocalAnime(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        releaseYear = this.releaseYear,
        episode = this.episode,
        score = this.score,
        rank = this.rank,
    )
}

fun List<NetworkAnime>.toLocal(): List<LocalAnime> = map(NetworkAnime::toLocal)

fun NetworkAnime.toExternal(): Anime {
    return Anime(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        releaseYear = this.releaseYear,
        score = this.score,
        episode = this.episode,
        rank = this.rank,
    )
}
