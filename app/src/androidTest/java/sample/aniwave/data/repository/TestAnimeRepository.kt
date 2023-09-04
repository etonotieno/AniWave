package sample.aniwave.data.repository

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import sample.aniwave.data.model.Anime
import java.io.File

class TestAnimeRepository : AnimeRepository {

    private val animeStream: MutableSharedFlow<List<Anime>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun observeAll(): Flow<List<Anime>> {
        return animeStream
    }

    override suspend fun refresh() {
        TODO("Not yet implemented")
    }

    override suspend fun searchAnime(photo: File): Anime? {
        TODO("Not yet implemented")
    }
}
