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
package sample.aniwave.data.source.local.model

import org.junit.Assert.assertEquals
import org.junit.Test
import sample.aniwave.data.model.Anime

class LocalAnimeTest {

    @Test
    fun localAnime_canBeMappedTo_externalAnime() {
        val localAnime = LocalAnime(
            id = 1,
            imageUrl = "",
            title = "",
            releaseYear = "",
            score = 0.0,
            episode = 1,
            rank = 1,
        )

        val anime = localAnime.toExternal()

        val expectedAnime = Anime(
            id = 1,
            imageUrl = "",
            title = "",
            releaseYear = "",
            score = 0.0,
            episode = 1,
            rank = 1,
        )
        assertEquals(expectedAnime, anime)
    }
}
