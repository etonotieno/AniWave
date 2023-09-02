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
import sample.aniwave.data.source.network.model.NetworkAnime
import sample.aniwave.data.source.network.model.toLocal
import javax.inject.Inject

class DefaultAnimeRepository @Inject constructor(
    private val localAnimeDataSource: LocalAnimeDataSource,
    private val networkDataSource: NetworkAnimeDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : AnimeRepository {

    override fun observeAll(): Flow<List<Anime>> {
        return localAnimeDataSource.observeAll().map(List<LocalAnime>::toExternal)
    }

    suspend fun loadAnime() {
        val networkAnime = networkDataSource.getTopAnime()
        localAnimeDataSource.upsertAll(networkAnime.map(NetworkAnime::toLocal))
    }

    override suspend fun refresh() {
        val networkAnime = networkDataSource.getTopAnime()
        localAnimeDataSource.deleteAll()
        val localAnime = withContext(dispatcherProvider.default) {
            networkAnime.toLocal()
        }
        localAnimeDataSource.upsertAll(localAnime)
    }
}
