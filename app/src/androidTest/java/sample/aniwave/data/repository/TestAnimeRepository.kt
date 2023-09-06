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
