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

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import sample.aniwave.data.source.local.model.LocalAnime
import kotlin.random.Random

class AnimeDaoTest {

    private lateinit var database: AniWaveDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AniWaveDatabase::class.java,
        ).allowMainThreadQueries().build()
    }

    @Test
    fun upsertAll_savesLocalAnime() = runTest {
        val testData = saveLocalAnime()

        val anime = database.animeDao().observeAll().first()

        assertEquals(testData.size, anime.size)
        assertEquals(testData, anime)
    }

    @Test
    fun deleteAll_deletesLocalAnime() = runTest {
        // GIVEN: A database with LocalAnime
        saveLocalAnime()
        // WHEN: deleteAll is called
        database.animeDao().deleteAll()

        // THEN: All data is deleted in the Database
        val anime = database.animeDao().observeAll().first()
        assertEquals(0, anime.size)
    }

    private suspend fun saveLocalAnime(): List<LocalAnime> {
        val anime = mutableListOf<LocalAnime>()
        repeat(5) { index ->
            anime.add(
                LocalAnime(
                    id = index,
                    imageUrl = "url:$index",
                    title = "Anime $index",
                    releaseYear = index,
                    score = Random.nextDouble(0.0, 10.0),
                    episode = index,
                    rank = index,
                ),
            )
        }

        database.animeDao().upsertAll(anime)

        return anime
    }
}
