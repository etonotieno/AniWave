package sample.aniwave.data.repository

import kotlinx.coroutines.flow.Flow
import sample.aniwave.data.model.Anime
import java.io.File

interface AnimeRepository {

    fun observeAll(): Flow<List<Anime>>

    suspend fun refresh()

    suspend fun searchAnime(photo: File): Anime?
}
