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

import kotlinx.coroutines.flow.Flow
import sample.aniwave.data.source.AnimeDataSource
import sample.aniwave.data.source.local.model.LocalAnime
import javax.inject.Inject

/**
 * [LocalAnime] data source backed [AnimeDao]
 */
class LocalAnimeDataSource @Inject constructor(
    private val animeDao: AnimeDao,
) : AnimeDataSource {

    fun observeAll(): Flow<List<LocalAnime>> = animeDao.observeAll()

    suspend fun upsertAll(anime: List<LocalAnime>) = animeDao.upsertAll(anime)

    suspend fun deleteAll() = animeDao.deleteAll()
}
