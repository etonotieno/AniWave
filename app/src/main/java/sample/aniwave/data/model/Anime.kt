package sample.aniwave.data.model

import sample.aniwave.data.source.local.model.LocalAnime

/**
 * External data model exposed to other layers of the app outside the data layer
 */
data class Anime(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: String,
    val rating: Double,
)

fun Anime.toLocal(): LocalAnime = LocalAnime(
    id = this.id,
    imageUrl = this.imageUrl,
    title = this.title,
    releaseYear = this.releaseYear,
    rating = this.rating
)

fun List<Anime>.toLocal(): List<LocalAnime> = map(Anime::toLocal)
