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
        @ApplicationContext context: Context
    ): AniWaveDatabase {
        return Room.databaseBuilder(
            context,
            AniWaveDatabase::class.java,
            "aniwave-database"
        ).build()
    }

    @Provides
    fun provideAnimeDao(database: AniWaveDatabase): AnimeDao {
        return database.animeDao()
    }
}
