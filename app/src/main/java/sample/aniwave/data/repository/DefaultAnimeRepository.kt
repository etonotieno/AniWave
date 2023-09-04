package sample.aniwave.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import sample.aniwave.data.coroutines.DispatcherProvider
import sample.aniwave.data.model.Anime
import sample.aniwave.data.source.local.LocalAnimeDataSource
import sample.aniwave.data.source.local.model.LocalAnime
import sample.aniwave.data.source.local.model.toExternal
import sample.aniwave.data.source.network.NetworkAnimeDataSource
import sample.aniwave.data.source.network.model.toExternal
import sample.aniwave.data.source.network.model.toLocal
import java.io.File
import javax.inject.Inject

class DefaultAnimeRepository @Inject constructor(
    private val localAnimeDataSource: LocalAnimeDataSource,
    private val networkDataSource: NetworkAnimeDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : AnimeRepository {

    override fun observeAll(): Flow<List<Anime>> {
        return localAnimeDataSource.observeAll().map(List<LocalAnime>::toExternal)
    }

    override suspend fun refresh() {
        val networkAnime = networkDataSource.getTopAnime()
        localAnimeDataSource.deleteAll()
        val localAnime = withContext(dispatcherProvider.default) {
            networkAnime.toLocal()
        }
        localAnimeDataSource.upsertAll(localAnime)
    }

    override suspend fun searchAnime(photo: File): Anime? {
        return networkDataSource.searchAnime(photo)?.toExternal()
    }
}
