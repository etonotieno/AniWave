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
package sample.aniwave.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AnimeRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    private lateinit var animeRepository: AnimeRepository

    @Test
    fun observeAll_exposesLocalAnime() = runTest {
//        val anime = animeRepository.observeAll().first()
//        assertEquals(localAnime.toExternal(), anime)
    }

    @Test
    fun onAnimeLoaded_localDataIsUpdated() = testScope.runTest {
//        animeRepository.loadAnime()
//        val localAnime = localDataSource.observeAll().first()
//        assertEquals(1, localAnime.size)
    }

    @Test
    fun onRefresh_localIsEqualToNetwork() = testScope.runTest {
//        val networkAnime = listOf<NetworkLAnime>()
//        animeRepository.refresh()
//
//        val localAnime = localDataSource.observeAll().first()
//        assertEquals(networkAnime.toLocal(), localAnime)
    }
}
