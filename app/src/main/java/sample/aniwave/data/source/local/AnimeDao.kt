package sample.aniwave.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import sample.aniwave.data.source.local.model.LocalAnime

@Dao
interface AnimeDao {

    /**
     * Each time the data changes a new item is emitted to the Flow allowing the callers to listen
     * to changes, removing the need to poll the data.
     */
    @Query("SELECT * FROM anime")
    fun observeAll(): Flow<List<LocalAnime>>

    /**
     * One-shot I/O operations
     */
    @Upsert
    suspend fun upsertAll(anime: List<LocalAnime>)

    @Query("DELETE FROM anime")
    suspend fun deleteAll()
}
