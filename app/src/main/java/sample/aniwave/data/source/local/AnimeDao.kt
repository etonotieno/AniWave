/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    @Query("SELECT * FROM anime ORDER BY rank ASC")
    fun observeAll(): Flow<List<LocalAnime>>

    /**
     * One-shot I/O operations
     */
    @Upsert
    suspend fun upsertAll(anime: List<LocalAnime>)

    @Query("DELETE FROM anime")
    suspend fun deleteAll()
}
