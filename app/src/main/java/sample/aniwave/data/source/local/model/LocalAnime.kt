package sample.aniwave.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import sample.aniwave.data.model.Anime
import sample.aniwave.data.source.network.model.NetworkAnime

/**
 * Anime representation that will be saved locally in an [androidx.room.RoomDatabase]
 */
@Entity(tableName = "anime")
data class LocalAnime(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: String,
    val rating: Double,
)

fun LocalAnime.toExternal(): Anime {
    return Anime(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        releaseYear = this.releaseYear,
        rating = this.rating
    )
}

fun List<LocalAnime>.toExternal(): List<Anime> = map(LocalAnime::toExternal)

fun LocalAnime.toNetwork(): NetworkAnime {
    return NetworkAnime(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        episode = 0,
    )
}

fun List<LocalAnime>.toNetwork(): List<NetworkAnime> = map(LocalAnime::toNetwork)
