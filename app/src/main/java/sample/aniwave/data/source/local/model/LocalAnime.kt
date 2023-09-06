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

import androidx.room.Entity
import androidx.room.PrimaryKey
import sample.aniwave.data.model.Anime

/**
 * Anime representation that will be saved locally in an [androidx.room.RoomDatabase]
 */
@Entity(tableName = "anime")
data class LocalAnime(
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: Int,
    val score: Double,
    val episode: Int,
    val rank: Int,
)

fun LocalAnime.toExternal(): Anime {
    return Anime(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        releaseYear = this.releaseYear,
        score = this.score,
        episode = this.episode,
        rank = this.rank,
    )
}

fun List<LocalAnime>.toExternal(): List<Anime> = map(LocalAnime::toExternal)
