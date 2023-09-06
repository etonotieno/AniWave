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
package sample.aniwave.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sample.aniwave.data.source.local.AniWaveDatabase
import sample.aniwave.data.source.local.AnimeDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAniWaveDatabase(
        @ApplicationContext context: Context,
    ): AniWaveDatabase {
        return Room.databaseBuilder(
            context,
            AniWaveDatabase::class.java,
            "aniwave-database",
        ).build()
    }

    @Provides
    fun provideAnimeDao(database: AniWaveDatabase): AnimeDao {
        return database.animeDao()
    }
}
