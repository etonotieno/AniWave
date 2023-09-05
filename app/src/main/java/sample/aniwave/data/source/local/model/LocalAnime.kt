package sample.aniwave.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import sample.aniwave.data.model.Anime

/**
 * Anime representation that will be saved locally in an [androidx.room.RoomDatabase]
 */
@Entity(tableName = "anime")
data class LocalAnime(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: Int,
    val score: Double,
    val episode: Int,
    val rank: Int,
)

fun LocalAnime.toExternal(): Anime {
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

fun List<LocalAnime>.toExternal(): List<Anime> = map(LocalAnime::toExternal)
