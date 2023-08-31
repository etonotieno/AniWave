package sample.aniwave.data.source.local

import kotlinx.coroutines.flow.Flow
import sample.aniwave.data.source.AnimeDataSource
import sample.aniwave.data.source.local.model.LocalAnime
import javax.inject.Inject

/**
 * [LocalAnime] data source backed [AnimeDao]
 */
class LocalAnimeDataSource @Inject constructor(
    private val animeDao: AnimeDao
) : AnimeDataSource {

    fun observeAll(): Flow<List<LocalAnime>> = animeDao.observeAll()

    suspend fun upsertAll(anime: List<LocalAnime>) = animeDao.upsertAll(anime)

    suspend fun deleteAll() = animeDao.deleteAll()
}
