package sample.aniwave.data.model

/**
 * External data model exposed to other layers of the app outside the data layer
 */
data class Anime(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: Int,
    val score: Double,
    val episode: Int,
    val rank: Int,
)
